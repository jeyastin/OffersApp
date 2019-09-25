package com.offers.demo.transport;

import java.util.List;

import lombok.Data;

@Data
public class OutletTO {

	
	private Integer outletId;
	
	private String outletName;
	
	private int radius;
	
	private String industry;
	
	private List<OfferTO> offers;
}
