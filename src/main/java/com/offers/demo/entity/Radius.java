package com.offers.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="RADIUS")
public class Radius implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="RADIUS_ID")
	private Integer radius;	
	
	
}
