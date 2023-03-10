package com.imobiliare360.repository;



import com.imobiliare360.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
//@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    //@Query(value = "SELECT * FROM bill WHERE home_id = ?1", nativeQuery = true)
    //@Query(value = "SELECT * FROM bill WHERE home_id = ?1", nativeQuery = true)
    List<BillEntity> findByHomeId(Long homeId);
    //List<BillEntity> findByUserId(Long userId);


//    @Query(value = "SELECT * FROM bill", nativeQuery = true)
//    List<BillEntity> findAll();


    @Query(value = "SELECT * FROM bill WHERE id = ?1", nativeQuery = true)
    BillEntity getById(@Param("id") Long idNumeric);


    @Modifying
    @Query(value = "DELETE FROM favorite_home WHERE home_id = ?1", nativeQuery = true)
    void deleteById(@Param("home_id") Long idNumeric);

    void delete(BillEntity billEntity);


    //BillEntity save(BillEntity billEntity);


}
