package com.offers.demo.transport;

import java.util.List;

import lombok.Data;

@Data
public class LookupTO {	
	
	private List<String> industry;
	private List<Integer> radius;
	private List<String> outlets;

}
