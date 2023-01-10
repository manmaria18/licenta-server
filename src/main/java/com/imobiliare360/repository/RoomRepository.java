package com.imobiliare360.repository;

import com.imobiliare360.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoomRepository extends JpaRepository<RoomEntity, Long>
{
//    @Modifying
//    @Query(value = "DELETE FROM home_rooms WHERE home_entity_id = :homeId", nativeQuery = true)
//    void deleteHomeRelations(@Param("homeId") Long homeId);

}
