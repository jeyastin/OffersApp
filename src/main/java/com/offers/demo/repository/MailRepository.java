package com.offers.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offers.demo.entity.Mail;

public interface MailRepository extends JpaRepository<Mail, Integer> {

}
