package com.offers.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offers.demo.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, String> {
	
}
