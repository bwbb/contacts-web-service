package com.brandon.contactsdatabase.service.impl;


import com.brandon.contactsdatabase.domain.dao.PersonDAO;
import com.brandon.contactsdatabase.domain.dto.CallableContactDTO;
import com.brandon.contactsdatabase.domain.dto.ContactDTO;
import com.brandon.contactsdatabase.domain.enumeration.PhoneType;
import com.brandon.contactsdatabase.domain.jpa.Address;
import com.brandon.contactsdatabase.domain.jpa.Person;
import com.brandon.contactsdatabase.domain.jpa.PersonName;
import com.brandon.contactsdatabase.domain.jpa.Phone;
import org.assertj.core.util.Lists;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactsServiceImplTest {

	@Mock
	private PersonDAO personDAO;

	@InjectMocks
	private ContactsServiceImpl service;

	@Captor
	private ArgumentCaptor<Person> personCaptor;


	@Test
	public void getAllContacts() {
		List<Person> mockPersons = Lists.newArrayList( createPerson(), createPerson() );
		doReturn( mockPersons ).when( personDAO ).findAll();

		List<ContactDTO> results = service.getAllContacts();

		assertEquals( 2, results.size() );
		verify( personDAO ).findAll();
	}

	@Test
	public void create() {
		ContactDTO contact = new ContactDTO( createPerson() );
		when( personDAO.save(any(Person.class)) ).then( returnsFirstArg() );
		service.create( contact );

		verify( personDAO ).save( personCaptor.capture() );
		Person person = personCaptor.getValue();

		assertEquals( "John", person.getName().getFirst() );
	}

	@Test
	public void updatePersonNoPersonFound() {
		Person person = createPerson();
		ContactDTO contact = new ContactDTO( person );
		person.setName( new PersonName("John", null, "Smith") );
		contact.getName().setFirst( "Mary" );

		service.update( 1l, contact );

		assertEquals( "John", person.getName().getFirst() );
	}

	@Test
	public void updatePersonWhenContactHasNoAddressShouldDeleteExistingAddress() {
		Person person = createPerson();
		person.setAddress( null );

		ContactDTO contact = new ContactDTO( person );
		person.setAddress( new Address() );

		contact.getName().setFirst( "Mary" );
		person.setName( new PersonName("John", null, "Smith") );

		doReturn( person ).when( personDAO ).getById( anyLong() );
		service.update( 1l, contact );

		assertEquals( "Mary", person.getName().getFirst() );
		assertNull( person.getAddress() );
	}

	@Test
	public void getById() {
		Person person = createPerson();
		doReturn( person ).when( personDAO ).getById( anyLong() );

		ContactDTO result = service.getById( 1l );

		assertEquals( "John", result.getName().getFirst() );
		verify( personDAO ).getById( 1l );
	}

	@Test
	public void delete() {
		Person person = createPerson();
		doReturn( person ).when( personDAO ).getById( anyLong() );

		service.deleteById( 1l );

		assertEquals( 0, person.getPhones().size() );
		verify( personDAO ).delete( person );
	}

	@Test
	public void getCallList() {
		Person personWithHomePhone = createPerson( "Michael" );
		personWithHomePhone.getPhones().add( new Phone( "222-222-2222", PhoneType.HOME  ));

		List<Person> mockPersons = Lists.newArrayList( personWithHomePhone, createPerson() );
		doReturn( mockPersons ).when( personDAO ).findAll( ContactsServiceImpl.CALL_LIST_SORT );

		List<CallableContactDTO> results = service.getCallList();

		assertEquals( 1, results.size() );
		assertEquals( "Michael", results.get(0).getName().getFirst() );

		verify( personDAO ).findAll( ContactsServiceImpl.CALL_LIST_SORT );
	}

	private Person createPerson() {
		return createPerson("John");
	}

	private Person createPerson(String firstName) {
		Person person = new Person();
		person.setName( new PersonName(firstName, null, "Smith") );
		person.setEmail( "john.smith@gmail.com" );

		person.setAddress( new Address("123 Main", "New York", "NY", "11111") );

		Phone phone = new Phone( "111-111-1111", PhoneType.MOBILE );
		person.getPhones().add( phone );

		return person;
	}

}
