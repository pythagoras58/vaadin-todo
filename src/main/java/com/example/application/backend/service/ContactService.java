package com.example.application.backend.service;

import com.example.application.backend.repository.CompanyRepository;
import com.example.application.backend.repository.ContactRepository;
import org.springframework.stereotype.Service;

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
}
