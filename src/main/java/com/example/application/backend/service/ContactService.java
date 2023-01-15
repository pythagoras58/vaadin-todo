package com.example.application.backend.service;

import com.example.application.backend.entities.CompanyEntity;
import com.example.application.backend.entities.ContactEntity;
import com.example.application.backend.repository.CompanyRepository;
import com.example.application.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContactService {
    private static final Logger LOGGER = Logger.getLogger(ContactService.class.getName());

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public ContactService(ContactRepository contactRepository, CompanyRepository companyRepository){
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
    }

    public List<ContactEntity> findAll(){
        return contactRepository.findAll();
    }
    public List<ContactEntity> findAll(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return contactRepository.findAll();
        }else{
            return  contactRepository.search(filterText);
        }

    }

    public long count(){
        return contactRepository.count();
    }

    public void delete(ContactEntity contact){
        contactRepository.delete(contact);
    }

    public void save(ContactEntity contact){
        if(contact == null){
            LOGGER.log(Level.SEVERE, "Contact is null");
            return;
        }
        contactRepository.save(contact);
    }

    // add test data
    @PostConstruct
    public void populateTestData() {
        if (companyRepository.count() == 0) {
            companyRepository.saveAll(
                    Stream.of("Path-Way Electronics", "E-Tech Management", "Path-E-Tech Management")
                                    .map(CompanyEntity::new)
                                    .collect(Collectors.toList())
            );
        }
        if (contactRepository.count() == 0) {
            Random r = new Random(0);
            List<CompanyEntity> companies = companyRepository.findAll();
            contactRepository.saveAll(
                    Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                                    "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
                                    "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson",
                                    "Kelly Gustavsson",
                                    "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
                                    "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson ", "Lara Martin",
                                    "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
                                    "Jaydan Jackson", "Bernard Nilsen")
                            .map(name -> {
                                String[] split = name.split(" ");
                                ContactEntity contact = new ContactEntity();
                                contact.setFirstName(split[0]);
                                contact.setLastName(split[1]);
                                contact.setCompany(companies.get(r.nextInt(companies.size())));
                                contact.setStatus(ContactEntity.Status.values()[r.nextInt(ContactEntity
                                        .Status.values().length)]);
                                String email = (contact.getFirstName() + "." + contact
                                        .getLastName() + "@" + contact.getCompany().getName().replaceAll("[\\s-]", "") +
                                        ".com").toLowerCase();
                                contact.setEmail(email);
                                return contact;
                            }).collect(Collectors.toList()));
        }
    }
}
