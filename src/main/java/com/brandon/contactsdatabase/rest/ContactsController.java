package com.brandon.contactsdatabase.rest;

import com.brandon.contactsdatabase.domain.dto.ContactDTO;
import com.brandon.contactsdatabase.service.interfaces.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

	@Autowired
	private ContactsService contactsService;


	@GetMapping
	public List<ContactDTO> getAllContacts() {
		return contactsService.getAllContacts();
	}

	@PostMapping
	public void createContact(@RequestBody ContactDTO contactDTO) {
		contactsService.create( contactDTO );
	}

	@PutMapping("/{id}")
	public void createContact(@PathVariable("id") long contactId, @RequestBody ContactDTO contactDTO) {
		contactsService.update( contactId, contactDTO );
	}

	@GetMapping("/{id}")
	public ContactDTO getContactById(@PathVariable("id") long contactId) {
		return contactsService.getById( contactId );
	}

	@DeleteMapping("/{id}")
	public void deleteContactById(@PathVariable("id") long contactId) {
		contactsService.deleteById( contactId );
	}

	@GetMapping("/call-list")
	public List<ContactDTO> getCallList() {
		return contactsService.getCallList();
	}
}
