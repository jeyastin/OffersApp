package com.offers.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="USER")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="EMAIL")
	private String email;
	@Column(name="NAME")
	private String name;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<UserOutlet> outlets;

}
