package com.imobiliare360.repository;

import com.imobiliare360.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
}
