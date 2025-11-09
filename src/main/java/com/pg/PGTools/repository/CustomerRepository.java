package com.pg.PGTools.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pg.PGTools.entity.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long> {

}
