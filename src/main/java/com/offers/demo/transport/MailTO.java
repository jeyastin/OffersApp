package com.offers.demo.transport;

import lombok.Data;

@Data
public class MailTO {
	
	private String mailTo;
	private String mailFrom;	
	private OfferTO offerTO;
	private String status;

}
