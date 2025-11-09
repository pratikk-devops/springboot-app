package com.pg.PGTools.service;

import java.util.List;

import com.pg.PGTools.entity.InductrieWork;

public interface InductrieWorkService {

//	Save Inductries
	void save(InductrieWork inductrie);
	
//	Get All Inductries
	List<InductrieWork> getAllInductries();
	
	boolean deleteById(Long id);
}
