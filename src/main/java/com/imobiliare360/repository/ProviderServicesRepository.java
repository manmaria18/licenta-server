package com.imobiliare360.repository;

import com.imobiliare360.entity.ProviderServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

//@Repository
@Transactional
public interface ProviderServicesRepository extends JpaRepository<ProviderServiceEntity, Long> {
    //void save(ProvidedServiceEntity providedSeviceEntity);
}
