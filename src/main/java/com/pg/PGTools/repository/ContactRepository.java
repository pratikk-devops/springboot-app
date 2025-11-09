package com.pg.PGTools.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pg.PGTools.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
}
