package com.offers.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="MAIL")
public class Mail implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MAIL_ID")
	private Integer mailId;
	
	@Column(name="MAIL_BODY")
	private String body;
	
	@Column(name="MAIL_FROM")
	private String mailFrom;
	
	@Column(name="MAIL_TO")
	private String mailTo;
	
	@Column(name="MAIL_STATUS")
	private String status;
	
	
}
