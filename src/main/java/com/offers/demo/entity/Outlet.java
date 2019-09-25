package com.offers.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="OUTLET")
public class Outlet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OUTLET_ID")
	private Integer outletId;
	@Column(name="OUTLET_NAME")
	private String outletName;
	@Column(name="OUTLET_RADIUS")
	private int radius;
	@Column(name="OUTLET_INDUSTRY")
	private String industry;
	
	@OneToMany(mappedBy="outlet", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Offer> offers;
	
	public Outlet(Integer outletId){
		this.outletId=outletId;
	}
	
	public Outlet(){
	
	}

}
