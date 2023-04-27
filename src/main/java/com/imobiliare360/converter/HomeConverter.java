package com.imobiliare360.converter;

import com.imobiliare360.dto.*;
import com.imobiliare360.entity.HomeEntity;
import com.imobiliare360.entity.LocationEntity;
import com.imobiliare360.repository.BillRepository;
import com.imobiliare360.repository.FavoriteHomeRepository;
import com.imobiliare360.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HomeConverter {
    @Autowired
    private FavoriteHomeRepository favoriteHomeRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProviderServiceConverter providerServiceConvertor;

    public UserDto userEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());

        return userDto;
    }

    public LocationDto locationEntityToDto(LocationEntity locationEntity) {

        return new LocationDto(locationEntity.getLatitude(), locationEntity.getLongitude());
    }

    public HomeDto homeEntityToDto(HomeEntity homeEntity) {
        HomeDto homeDto = new HomeDto();
        homeDto.setId(homeEntity.getId());
        homeDto.setName(homeEntity.getName());
        //homeDto.setDescription(homeEntity.getDescription());
        if(homeEntity.getLocation()!=null){
            homeDto.setLocation(locationEntityToDto(homeEntity.getLocation()));
        }
        homeDto.setUserDto(userEntityToDto(homeEntity.getUser()));
        List<ProviderServicesDto> providerServicesDtoList = homeEntity.getServices().stream().map(service -> {
            return providerServiceConvertor.convertToDto(service);
        }) .collect(Collectors.toList());;
        homeDto.setServices(providerServicesDtoList);

       List<BillDto> bills =billRepository.findByHomeId(homeEntity.getId())
                .stream()
                .map(currentBill ->
                {
                    return new BillDto(
                            currentBill.getId(),
                            currentBill.getSum(),
                            currentBill.getHome().getId() ,
                            providerServiceConvertor.convertToDto(currentBill.getProviderService()),
                            currentBill.getIssueDate(),
                            currentBill.getDeadline(),
                            new BillStatusDto(currentBill.getStatus().getId(),currentBill.getStatus().getStatus())


                    );
                })
                .collect(Collectors.toList());

        //List<BillDto> bills2 = billRepository.search(homeEntity.getId());
        homeDto.setBills(bills);

        return homeDto;

    }

    public List<HomeDto> homeEntitiesToDtos(List<HomeEntity> homeEntities) {
        return homeEntities.stream()
                .map(currentHome -> homeEntityToDto(currentHome)
                )
                .collect(Collectors.toList());
    }

}
