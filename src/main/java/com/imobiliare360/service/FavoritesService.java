package com.imobiliare360.service;

import com.imobiliare360.converter.HomeConverter;
import com.imobiliare360.dto.FavoritesListDto;
import com.imobiliare360.dto.HomeDto;
import com.imobiliare360.entity.FavoriteHomeEntity;
import com.imobiliare360.entity.HomeEntity;
import com.imobiliare360.repository.FavoriteHomeRepository;
import com.imobiliare360.repository.HomeRepository;
import com.imobiliare360.security.model.User;
import com.imobiliare360.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesService {

    @Autowired
    private FavoriteHomeRepository favoriteHomeRepository;
    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeConverter homeConverter;

    public void like(Long userId, Long hoseId) {
        if (contains(userId, hoseId))
        {
            return;
        }

        User user = userRepository.getById(userId);
        HomeEntity homeEntity = homeRepository.getById(hoseId);
        FavoriteHomeEntity favoriteHomeEntity = new FavoriteHomeEntity(user, homeEntity);

        favoriteHomeRepository.save(favoriteHomeEntity);
    }

    public void unlike(Long userId, Long hoseId) {
        if (!contains(userId, hoseId))
        {
            return;
        }

        favoriteHomeRepository.deleteByUserIdAndHouseId(userId, hoseId);
    }

    public FavoritesListDto getAllForUser(Long userId) {
        List<FavoriteHomeEntity> favoriteHomeEntities = favoriteHomeRepository.findByUserId(userId);

        List<HomeEntity> homeEntities = favoriteHomeEntities.stream().map(favHome -> favHome.getHome()).collect(Collectors.toList());
        List<HomeDto> homeDtos = homeConverter.homeEntitiesToDtos(homeEntities);
        FavoritesListDto favoritesListDto = new FavoritesListDto(userId, homeDtos);

        return favoritesListDto;
    }

    public boolean contains(Long userId, Long houseId) {
        return favoriteHomeRepository.findByUserIdAndHouseId(userId, houseId).size() > 0;
    }
}