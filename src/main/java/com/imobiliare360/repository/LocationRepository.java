package com.imobiliare360.repository;

import com.imobiliare360.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface LocationRepository extends JpaRepository<LocationEntity, Long>
{
}
