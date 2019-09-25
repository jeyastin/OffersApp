package com.offers.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offers.demo.service.OutletService;
import com.offers.demo.transport.LookupTO;
import com.offers.demo.transport.OutletTO;

@RestController
public class OutletController {
	
	@Autowired
	OutletService outletService;
	
	
	@PostMapping("/addOutletOffers")
	@CrossOrigin
	public OutletTO addOutletOffers(HttpServletRequest request, HttpServletResponse response, @RequestBody OutletTO outletTO){
		OutletTO reponse = outletService.addOutletOffers(outletTO);
		return reponse;
		
	}
	
	@GetMapping("/getLookup")
	@CrossOrigin
	public LookupTO getLookup(HttpServletRequest request, HttpServletResponse response){
		return outletService.getLookup();
		
	}
	
	@GetMapping("/search")
	@CrossOrigin
	public List<OutletTO> search(HttpServletRequest request, HttpServletResponse response
			, @RequestParam String industry , @RequestParam int radius){
		return outletService.search(industry,radius);
		
	}
	
	@GetMapping("/outlet/{name}")
	@CrossOrigin
	public OutletTO getOutlet(HttpServletRequest request, HttpServletResponse response
			, @PathVariable String name ){
		return outletService.getOutlet(name);
		
	}
	
	

}
