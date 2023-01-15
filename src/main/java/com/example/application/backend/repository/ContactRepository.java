package com.example.application.backend.repository;

import com.example.application.backend.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    // Search by text input
    @Query("select c from ContactEntity c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<ContactEntity> search(@Param("searchTerm") String filterText);
}
