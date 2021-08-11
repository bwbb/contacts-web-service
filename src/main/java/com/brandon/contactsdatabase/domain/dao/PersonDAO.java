package com.brandon.contactsdatabase.domain.dao;

import com.brandon.contactsdatabase.domain.jpa.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends JpaSpecificationExecutor<Person>, JpaRepository<Person, Long> {

}
