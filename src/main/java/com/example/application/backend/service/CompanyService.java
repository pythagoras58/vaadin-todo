package com.example.application.backend.service;

import com.example.application.backend.entities.CompanyEntity;
import com.example.application.backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CompanyService {
    private static final Logger LOGGER = Logger.getLogger(ContactService.class.getName());
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyEntity> findAll(){
        return companyRepository.findAll();
    }

    public void save(CompanyEntity company){
        if(company == null){
            LOGGER.log(Level.SEVERE, "Company Cannot Be Null");
            return;
        }

        companyRepository.save(company);
    }
}
