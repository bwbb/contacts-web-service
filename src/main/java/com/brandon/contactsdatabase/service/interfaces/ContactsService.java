package com.brandon.contactsdatabase.service.interfaces;

import com.brandon.contactsdatabase.domain.dto.CallableContactDTO;
import com.brandon.contactsdatabase.domain.dto.ContactDTO;

import java.util.List;

public interface ContactsService {

	List<ContactDTO> getAllContacts();

	void create(ContactDTO contact);

	void update(long contactId, ContactDTO contact);

	ContactDTO getById(long contactId);

	void deleteById(long contactId);

	List<CallableContactDTO> getCallList();
}
