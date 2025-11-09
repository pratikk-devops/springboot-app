package com.pg.PGTools.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg.PGTools.entity.Contact;
import com.pg.PGTools.repository.ContactRepository;
import com.pg.PGTools.service.ContactService;

@Service
public class ContactServiceImp implements ContactService {

	@Autowired
    private ContactRepository contactRepository;
	
//	Save User Query
	 @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
	 
//	Get ALl User Query
	 @Override
	 public List<Contact> getAllContacts() {
	     return contactRepository.findAll();
	 }
	 
//   Delete User Query
	 @Override
	 public void deleteContact(Long id) {
	     contactRepository.deleteById(id);
	 }
}
