package com.offers.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.offers.demo.entity.Outlet;
import com.offers.demo.entity.User;
import com.offers.demo.entity.UserOutlet;
import com.offers.demo.repository.UserRepository;
import com.offers.demo.transport.OutletTO;
import com.offers.demo.transport.UserTO;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public UserTO addUserOutlets(UserTO userTO) {
		User user = new User();
		BeanUtils.copyProperties(userTO, user);
		List<UserOutlet> userOutlets = getUserOutlets(userTO.getOutlets(), user);
		user.setOutlets(userOutlets);
		user = userRepository.save(user);
		return populateUserTO(user);

	}

	private UserTO populateUserTO(User user) {
		UserTO userTO = new UserTO();
		userTO.setEmail(user.getEmail());
		userTO.setName(user.getName());
		userTO.setOutlets(populateOutlets(user.getOutlets()));
		return userTO;

	}

	private List<OutletTO> populateOutlets(List<UserOutlet> outlets) {
		List<OutletTO> list = new ArrayList();
		if (!CollectionUtils.isEmpty(outlets)) {
			for (UserOutlet userOutlet : outlets) {
				OutletTO outlet = new OutletTO();
				outlet.setOutletId(userOutlet.getOutlet().getOutletId());
				outlet.setOutletName(userOutlet.getOutlet().getOutletName());
				list.add(outlet);
			}
		}

		return list;
	}

	private List<UserOutlet> getUserOutlets(List<OutletTO> outlets, User user) {
		List<UserOutlet> userOutlets = new ArrayList();
		if (!CollectionUtils.isEmpty(outlets)) {
			for (OutletTO outletTO : outlets) {
				UserOutlet userOutlet = new UserOutlet();
				userOutlet.setOutlet(new Outlet(outletTO.getOutletId()));
				userOutlet.setUser(user);
				userOutlets.add(userOutlet);
			}
		}
		return userOutlets;
	}

	public UserTO getUserOutlets(String email) {
		Optional<User> optional = userRepository.findById(email);
		UserTO userTO = null;
		if(optional.isPresent()){
			User user = optional.get();
			userTO = populateUserTO(user);
		}
		return userTO;
	}

}
