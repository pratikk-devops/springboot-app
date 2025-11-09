package com.pg.PGTools.service;

import java.util.List;

import com.pg.PGTools.entity.Contact;

public interface ContactService {

//	Save User Query
	Contact saveContact(Contact contact);
	
//	Get ALl User Query
	List<Contact> getAllContacts();
	
//	Delete User Query
	void deleteContact(Long id);

}
