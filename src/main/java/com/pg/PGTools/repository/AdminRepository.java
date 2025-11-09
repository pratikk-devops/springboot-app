package com.pg.PGTools.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pg.PGTools.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {

	
	Optional<Admin> findByAdminEmail(String adminEmail);
	boolean existsByAdminEmail(String adminEmail);
}
