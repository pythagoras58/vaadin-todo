package com.example.application.backend.repository;

import com.example.application.backend.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Company extends JpaRepository<CompanyEntity,Long> {
}
