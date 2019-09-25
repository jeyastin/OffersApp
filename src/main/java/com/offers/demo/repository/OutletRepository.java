package com.offers.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.offers.demo.entity.Outlet;

public interface OutletRepository extends JpaRepository<Outlet, Integer> {

	@Query(value="select distinct o.industry from Outlet o")
	List<String> findDistinctIndustry();
	
	@Query(value="select distinct o.outletName from Outlet o")
	List<String> findDistinctOutlets();

	List<Outlet> findByIndustryAndRadiusLessThanEqual(String industry, int radius);
	
	Outlet findByOutletName(String name);
}
