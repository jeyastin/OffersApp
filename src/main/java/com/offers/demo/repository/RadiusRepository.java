package com.offers.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.offers.demo.entity.Radius;

public interface RadiusRepository extends JpaRepository<Radius, Integer> {

	@Query(value="select distinct r.radius from Radius r")
	List<Integer> findDistinctRadius();

}
