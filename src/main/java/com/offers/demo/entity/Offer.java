package com.offers.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="OFFER")
public class Offer implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="OFFER_NAME")
	private String offerName;
	
	@ManyToOne
	@JoinColumn(name="OUTLET_ID")
	private Outlet outlet;
	
	@Column(name="OFFER_ADDRESS")
	private String address;
	
	@Column(name="OFFER_PHONE")
	private String phone;
	
}
