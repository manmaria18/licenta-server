package com.imobiliare360.repository;

import com.imobiliare360.entity.FavoriteHomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FavoriteHomeRepository extends JpaRepository<FavoriteHomeEntity, Long>
{
    @Query(value = "SELECT * FROM favorite_home WHERE user_id = ?1", nativeQuery = true)
    List<FavoriteHomeEntity> findByUserId(Long userId);

    @Query(value = "SELECT * FROM favorite_home WHERE user_id = ?1 and home_id = ?2", nativeQuery = true)
    List<FavoriteHomeEntity> findByUserIdAndHouseId(Long userId, Long houseId);

    @Modifying
    @Query(value = "DELETE FROM favorite_home WHERE user_id = ?1 and home_id = ?2", nativeQuery = true)
    void deleteByUserIdAndHouseId(Long userId, Long houseId);

    void deleteByHomeId(Long id);
}
