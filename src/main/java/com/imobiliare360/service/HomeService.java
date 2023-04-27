package com.imobiliare360.service;

import com.imobiliare360.converter.HomeConverter;
import com.imobiliare360.dto.HomeDto;
import com.imobiliare360.dto.ProviderServicesDto;
import com.imobiliare360.dto.RoomDto;
import com.imobiliare360.entity.HomeEntity;
import com.imobiliare360.entity.LocationEntity;
import com.imobiliare360.entity.ProviderServiceEntity;
import com.imobiliare360.entity.RoomEntity;
import com.imobiliare360.repository.*;
import com.imobiliare360.security.model.User;
import com.imobiliare360.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeConverter homeConverter;
    @Autowired
    private FavoriteHomeRepository favoriteHomeRepository;

    @Autowired
    private ProviderServicesRepository providerServicesRepository;

    public List<HomeDto> getAllForUser(Long userId)
    {
        List<HomeEntity> homeEntities = homeRepository.findByUserId(userId);

        return homeConverter.homeEntitiesToDtos(homeEntities);
    }

    public List<HomeDto> getAll()
    {

        List<HomeEntity> homeEntities = homeRepository.findAll();

        return homeConverter.homeEntitiesToDtos(homeEntities);
    }


    public HomeDto get(Long id)
    {
        HomeEntity homeEntity = homeRepository.getById(id);

        return homeConverter.homeEntityToDto(homeEntity);
    }

    public HomeDto delete(Long id)
    {
        favoriteHomeRepository.deleteByHomeId(id);

        HomeEntity homeEntity = homeRepository.getById(id);
        //homeRepository.delete(homeEntity);
         homeRepository.deleteById(id);
        HomeDto homeDto = new HomeDto();
        homeDto.setId(homeEntity.getId());

        return homeDto;
    }

    public List<HomeDto> search(String name)
    {
        List<HomeEntity> homeEntities = homeRepository.search(name);
        return homeConverter.homeEntitiesToDtos(homeEntities);

    }

    public void save(HomeDto homeDto,

                     Long userId) throws IOException {


        HomeEntity homeEntity = new HomeEntity();
        homeEntity.setName(homeDto.getName());

        User user = userRepository.getById(userId);
        homeEntity.setUser(user);

        homeEntity = homeRepository.save(homeEntity);

        List<ProviderServiceEntity> services = new ArrayList<>();
        for(ProviderServicesDto service : homeDto.getServices()){
           ProviderServiceEntity serviceEntity = providerServicesRepository.getById(service.getId());
           services.add(serviceEntity);
        }
        homeEntity.setServices(services);

        LocationEntity locationEntity = new LocationEntity(homeDto.getLocation().getLatitude(), homeDto.getLocation().getLongitude(), homeEntity);

        locationRepository.save(locationEntity);

        homeEntity.setLocation(locationEntity);

        homeRepository.save(homeEntity);

    }

    public void edit(HomeDto homeDto,
                     Long userId) throws IOException {

        HomeEntity homeEntity = homeRepository.getById(homeDto.getId());
        homeEntity.setName(homeDto.getName());

        User user = userRepository.getById(userId);
        homeEntity.setUser(user);



        List<ProviderServiceEntity> services = new ArrayList<>();
        for(ProviderServicesDto service : homeDto.getServices()){
            ProviderServiceEntity serviceEntity = providerServicesRepository.getById(service.getId());
            services.add(serviceEntity);
        }
        homeEntity.setServices(services);

        LocationEntity locationEntity = new LocationEntity(homeDto.getLocation().getLatitude(), homeDto.getLocation().getLongitude(), homeEntity);

        locationRepository.save(locationEntity);

        homeEntity.setLocation(locationEntity);

        homeRepository.save(homeEntity);
    }
}
