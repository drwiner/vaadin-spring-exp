package org.vaadin.example.data.generator;

import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.example.data.entity.Company;
import org.vaadin.example.data.entity.Contact;
import org.vaadin.example.data.entity.Status;
import org.vaadin.example.data.repository.CompanyRepository;
import org.vaadin.example.data.repository.ContactRepository;
import org.vaadin.example.data.repository.StatusRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(ContactRepository contactRepository, CompanyRepository companyRepository,
                                      StatusRepository statusRepository) {

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (contactRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");
            ExampleDataGenerator<Company> companyGenerator = new ExampleDataGenerator<>(Company.class,
                    LocalDateTime.now());
            companyGenerator.setData(Company::setName, DataType.COMPANY_NAME);
            List<Company> companies = companyRepository.saveAll(companyGenerator.create(5, seed));

            List<Status> statuses = statusRepository
                    .saveAll(Stream.of("Imported lead", "Not contacted", "Contacted", "Customer", "Closed (lost)")
                            .map(Status::new).collect(Collectors.toList()));

            logger.info("... generating 50 Contact entities...");
            ExampleDataGenerator<Contact> contactGenerator = new ExampleDataGenerator<>(Contact.class,
                    LocalDateTime.now());
            contactGenerator.setData(Contact::setFirstName, DataType.FIRST_NAME);
            contactGenerator.setData(Contact::setLastName, DataType.LAST_NAME);
            contactGenerator.setData(Contact::setEmail, DataType.EMAIL);

            Random r = new Random(seed);
            List<Contact> contacts = contactGenerator.create(50, seed).stream().map(contact -> {
                contact.setCompany(companies.get(r.nextInt(companies.size())));
                contact.setStatus(statuses.get(r.nextInt(statuses.size())));
                return contact;
            }).collect(Collectors.toList());

            contactRepository.saveAll(contacts);

            logger.info("Generated demo data");
        };
    }

}
