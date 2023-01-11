package com.example.application.backend.service;

import com.example.application.backend.entities.CompanyEntity;
import com.example.application.backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyEntity> findAll(){
        return companyRepository.findAll();
    }
}
