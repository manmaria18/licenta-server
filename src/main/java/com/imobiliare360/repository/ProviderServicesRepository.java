package com.imobiliare360.repository;

import com.imobiliare360.entity.HomeEntity;
import com.imobiliare360.entity.ProviderServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

//@Repository
@Transactional
public interface ProviderServicesRepository extends JpaRepository<ProviderServiceEntity, Long> {
    //void save(ProvidedServiceEntity providedSeviceEntity);

}
