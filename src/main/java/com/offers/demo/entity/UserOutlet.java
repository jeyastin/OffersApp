package com.offers.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="USER_OUTLET")
public class UserOutlet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_OUTLET_ID")
	private Integer userOutletId;
	
	@OneToOne
	@JoinColumn(name="OUTLET_ID")
	private Outlet outlet;
	
	@ManyToOne
	@JoinColumn(name="EMAIL")
	private User user;

}
