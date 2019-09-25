package com.offers.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.offers.demo.entity.Offer;
import com.offers.demo.entity.Outlet;
import com.offers.demo.repository.OfferRepository;
import com.offers.demo.repository.OutletRepository;
import com.offers.demo.repository.RadiusRepository;
import com.offers.demo.repository.UserOutletRepository;
import com.offers.demo.transport.LookupTO;
import com.offers.demo.transport.MailTO;
import com.offers.demo.transport.OfferTO;
import com.offers.demo.transport.OutletTO;

@Service
public class OutletService {

	@Autowired
	OutletRepository outletRepository;

	@Autowired
	RadiusRepository radiusRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	UserOutletRepository userOutletRepository;
	
	@Autowired
	MailService mailService;

	public OutletTO addOutletOffers(OutletTO outletTO) {
		Outlet outlet = new Outlet();
		BeanUtils.copyProperties(outletTO, outlet);
		List<Offer> offers = getOffers(outletTO.getOffers(), outlet);
		List<Offer> toNotifyOffer = getNotifyOffers(offers);
		outlet.setOffers(offers);		
		outlet = outletRepository.save(outlet);
		List<Outlet> list = new ArrayList();
		List<OutletTO> listTO = new ArrayList();
		list.add(outlet);
		populateOutletTOs(listTO, list);
		sendUserNotification(toNotifyOffer);
		return listTO.get(0);
	}

	private void sendUserNotification(List<Offer> toNotifyOffer) {
		if (!CollectionUtils.isEmpty(toNotifyOffer)) {
			for (Offer offer : toNotifyOffer) {
				List<String> emails = userOutletRepository.findByOutletId(offer.getOutlet().getOutletId());
				notifyUsers(emails,offer);
			}
		}
		
	}

	private void notifyUsers(List<String> emails, Offer offer) {
		
		if (!CollectionUtils.isEmpty(emails)) {
			for (String email : emails) {
				MailTO mailTO = new MailTO();				
				mailTO.setMailTo(email);
				OfferTO offerTO = new OfferTO();
				BeanUtils.copyProperties(offer, offerTO);
				mailTO.setOfferTO(offerTO);
				mailService.sendMail(mailTO);
			}
		}
		
	}

	private List<Offer> getNotifyOffers(List<Offer> offers) {
		List<Offer> notify = new ArrayList<Offer>();
		if (!CollectionUtils.isEmpty(offers)) {
			for (Offer offer : offers) {
				boolean exists = offerRepository.existsById(offer.getOfferName());
				if(!exists){
					notify.add(offer);
				}
			}
		}
		return notify;
	}

	private List<Offer> getOffers(List<OfferTO> offerTOs, Outlet outlet) {
		List<Offer> offers = new ArrayList<Offer>();
		if (!CollectionUtils.isEmpty(offerTOs)) {
			for (OfferTO offerTO : offerTOs) {
				Offer offer = new Offer();
				BeanUtils.copyProperties(offerTO, offer);
				offer.setOutlet(outlet);
				offers.add(offer);
			}
		}
		return offers;
	}

	public LookupTO getLookup() {
		LookupTO lookupTO = new LookupTO();
		List<Integer> radius = radiusRepository.findDistinctRadius();
		List<String> industry = outletRepository.findDistinctIndustry();
		List<String> outlets = outletRepository.findDistinctOutlets();
		lookupTO.setIndustry(industry);
		lookupTO.setRadius(radius);
		lookupTO.setOutlets(outlets);
		return lookupTO;
	}

	public List<OutletTO> search(String industry, int radius) {
		List<OutletTO> outletTOs = new ArrayList();
		List<Outlet> outlets = outletRepository.findByIndustryAndRadiusLessThanEqual(industry, radius);
		populateOutletTOs(outletTOs, outlets);
		return outletTOs;
	}

	private void populateOutletTOs(List<OutletTO> outletTOs, List<Outlet> outlets) {

		if (!CollectionUtils.isEmpty(outlets)) {
			for (Outlet outlet : outlets) {
				OutletTO outletTO = new OutletTO();
				outletTO.setOutletId(outlet.getOutletId());
				outletTO.setOutletName(outlet.getOutletName());
				outletTO.setRadius(outlet.getRadius());
				outletTO.setIndustry(outlet.getIndustry());
				outletTO.setOffers(populateOfferTOs(outlet.getOffers()));
				outletTOs.add(outletTO);
			}
		}

	}

	private List<OfferTO> populateOfferTOs(List<Offer> offers) {
		List<OfferTO> offerTOs = new ArrayList();
		if (!CollectionUtils.isEmpty(offers)) {
			for (Offer offer : offers) {
				OfferTO offerTO = new OfferTO();
				offerTO.setOfferName(offer.getOfferName());
				offerTO.setOutletId(offer.getOutlet().getOutletId());
				offerTO.setAddress(offer.getAddress());
				offerTO.setPhone(offer.getPhone());
				offerTOs.add(offerTO);
			}
		}
		return offerTOs;
	}

	public OutletTO getOutlet(String name) {
		Outlet outlet = outletRepository.findByOutletName(name);
		List<Outlet> list = new ArrayList();
		List<OutletTO> listTO = new ArrayList();
		list.add(outlet);
		populateOutletTOs(listTO, list);
		return listTO.get(0);
	}

}
