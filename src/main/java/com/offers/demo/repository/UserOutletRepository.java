package com.offers.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.offers.demo.entity.UserOutlet;

public interface UserOutletRepository extends JpaRepository<UserOutlet, Integer> {

	@Query("select distinct u.user.email from UserOutlet u where u.outlet.outletId = ?1")
	List<String> findByOutletId(Integer outletId);

}
