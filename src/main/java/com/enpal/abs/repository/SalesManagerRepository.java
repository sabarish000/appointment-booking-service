package com.enpal.abs.repository;

import com.enpal.abs.model.SalesManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesManagerRepository extends JpaRepository<SalesManager, Integer> {
}
