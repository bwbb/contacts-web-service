package com.brandon.contactsdatabase.service.impl;

import com.brandon.contactsdatabase.domain.dao.PersonDAO;
import com.brandon.contactsdatabase.domain.dto.ContactDTO;
import com.brandon.contactsdatabase.domain.dto.PhoneDTO;
import com.brandon.contactsdatabase.domain.jpa.Address;
import com.brandon.contactsdatabase.domain.jpa.Person;
import com.brandon.contactsdatabase.domain.jpa.Phone;
import com.brandon.contactsdatabase.service.interfaces.ContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactsServiceImpl implements ContactsService {

	private static final Logger LOG = LoggerFactory.getLogger( ContactsServiceImpl.class );

	@Autowired
	private PersonDAO personDAO;

	@Override
	public List<ContactDTO> getAllContacts() {
		return personDAO.findAll().stream()
			.map( ContactDTO::new )
			.collect( Collectors.toList() );
	}

	@Override
	@Transactional
	public void create(ContactDTO contact) {
		Person person = new Person( contact );
		person = personDAO.save( person );

		savePhones( contact, person );
	}

	@Override
	@Transactional
	public void update(long personId, ContactDTO contact) {
		Person person = personDAO.getById( personId );

		if ( person == null ) {
			LOG.warn("No contact found for ID {}", personId);
			return;
		}

		LOG.info("Updating contact with ID {}", personId);

		person.setName( contact.getName() );
		person.setEmail( contact.getEmail() );

		if ( contact.getAddress() != null ) {
			if ( person.getAddress() == null ) {
				person.setAddress( new Address(contact.getAddress(), person) );
			} else {
				BeanUtils.copyProperties( contact.getAddress(), person.getAddress() );
			}
		} else {
			person.setAddress( null );
		}

		savePhones( contact, person );
	}

	private void savePhones(ContactDTO contact, Person person) {
		for ( PhoneDTO phoneDTO : contact.getPhone() ) {
			if ( phoneDTO == null ) {
				continue;
			}

			Phone existingPhone = person.getPhoneByType( phoneDTO.getType() );

			if ( existingPhone == null ) {
				person.getPhones().add( new Phone(phoneDTO, person) );
			} else {
				BeanUtils.copyProperties( phoneDTO, existingPhone );
			}
		}

		person.getPhones().removeIf(phone -> contact.getPhoneByType( phone.getType() ) == null);
	}


	@Override
	public ContactDTO getById(long personId) {
		return Optional.ofNullable( personDAO.getById(personId) )
				.map( ContactDTO::new )
				.orElse( null );
	}

	@Override
	@Transactional
	public void deleteById(long personId) {
		Person person = personDAO.getById( personId );
		if ( person != null ) {
			person.setAddress( null );
			person.getPhones().clear();
			personDAO.delete( person );
		}
	}

	@Override
	public List<ContactDTO> getCallList() {
		return null;
	}
}
