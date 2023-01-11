package com.example.application.backend.service;

import com.example.application.backend.entities.ContactEntity;
import com.example.application.backend.repository.CompanyRepository;
import com.example.application.backend.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ContactService {
    private static final Logger LOGGER = Logger.getLogger(ContactService.class.getName());

    private ContactRepository contactRepository;
    private CompanyRepository companyRepository;

    public ContactService(ContactRepository contactRepository, CompanyRepository companyRepository){
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
    }

    public List<ContactEntity> findAll(){
        return contactRepository.findAll();
    }

    public long count(){
        return contactRepository.count();
    }
}
