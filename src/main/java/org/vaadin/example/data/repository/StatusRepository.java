package org.vaadin.example.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.example.data.entity.Status;


public interface StatusRepository extends JpaRepository<Status, Integer> {

}
