package com.imobiliare360.repository;

import com.imobiliare360.entity.BillEntity;
import com.imobiliare360.entity.BillStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BillStatusRepository extends JpaRepository<BillStatusEntity, Long>{

}
