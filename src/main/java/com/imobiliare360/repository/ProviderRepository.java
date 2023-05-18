package com.imobiliare360.repository;

import javax.transaction.Transactional;



import com.imobiliare360.entity.BillEntity;
import com.imobiliare360.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

//@Repository
@Transactional
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long>{
    ProviderEntity findByName(String name);
}
