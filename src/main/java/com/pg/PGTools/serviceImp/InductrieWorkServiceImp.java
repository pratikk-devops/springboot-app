package com.pg.PGTools.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg.PGTools.entity.InductrieWork;
import com.pg.PGTools.repository.InductrieWorkRespository;
import com.pg.PGTools.service.InductrieWorkService;

@Service
public class InductrieWorkServiceImp implements InductrieWorkService {

	@Autowired
    private InductrieWorkRespository inductrieWorkRespository;

//	Save Inductries
    @Override
    public void save(InductrieWork inductrie) {
        inductrieWorkRespository.save(inductrie);
    }
    
//  Get All Inductries
    @Override
    public List<InductrieWork> getAllInductries() {
        return inductrieWorkRespository.findAll();
    }
    
    @Override
    public boolean deleteById(Long id) {
        if (inductrieWorkRespository.existsById(id)) {
            inductrieWorkRespository.deleteById(id);
            return true;
        }
        return false;
    }
}
