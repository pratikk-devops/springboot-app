package com.pg.PGTools.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg.PGTools.entity.Admin;
import com.pg.PGTools.repository.AdminRepository;
import com.pg.PGTools.service.AdminService;

@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
    private AdminRepository adminRepository;

//	Admin Register
    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

}
