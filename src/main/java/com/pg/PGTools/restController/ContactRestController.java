package com.pg.PGTools.restController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg.PGTools.entity.Contact;
import com.pg.PGTools.service.ContactService;

@RestController
@RequestMapping("/api")
public class ContactRestController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private JavaMailSender javaMailSender;	
	
//	Save User Query
    @PostMapping("/contact")
    public ResponseEntity<String> saveContact(@RequestBody Contact contact) {
        contactService.saveContact(contact);
        return ResponseEntity.ok("Contact saved successfully");
    }
    
//  Get All User Query
    @GetMapping("/allUserQuery")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
    
//  Reply to User
    @PostMapping("/replyUser")
    public ResponseEntity<String> replyUser(@RequestBody Map<String, String> data) {
        String toEmail = data.get("toEmail");
        String fullName = data.get("fullName");
        String message = data.get("message");

        String subject = "P&G Tools(india) Pvt. Ltd.";
        String emailBody = "Dear " + fullName + ",\n\n" + message + "\n\nRegards,\nP&G Tools(india) Pvt. Ltd.";

        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(toEmail);
            mail.setSubject(subject);
            mail.setText(emailBody);
            mail.setFrom("pgtoolsindiapvtltd@gmail.com");
            javaMailSender.send(mail);

            return ResponseEntity.ok("Mail sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }
    }
    
//  Delete User Query
    @DeleteMapping("/contact/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }

}
