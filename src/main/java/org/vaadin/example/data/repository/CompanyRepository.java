package org.vaadin.example.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.example.data.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
