package org.vaadin.example.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.example.data.entity.Contact;


public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
