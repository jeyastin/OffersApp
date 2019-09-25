package com.offers.demo.transport;

import java.util.List;

import lombok.Data;

@Data
public class UserTO {
	
	private String name;
	private String email;
	
	private List<OutletTO> outlets;
}
