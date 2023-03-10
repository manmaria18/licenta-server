package com.imobiliare360.converter;

import com.imobiliare360.dto.*;
import com.imobiliare360.entity.FavoriteHomeEntity;
import com.imobiliare360.entity.HomeEntity;
import com.imobiliare360.entity.LocationEntity;
import com.imobiliare360.repository.BillRepository;
import com.imobiliare360.repository.FavoriteHomeRepository;
import com.imobiliare360.security.UserPrincipal;
import com.imobiliare360.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HomeConverter {
    @Autowired
    private FavoriteHomeRepository favoriteHomeRepository;

    @Autowired
    private BillRepository billRepository;

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
        homeDto.setLocation(locationEntityToDto(homeEntity.getLocation()));
        homeDto.setUserDto(userEntityToDto(homeEntity.getUser()));

        homeDto.setLiked(false);
//        try {
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//            long id = ((UserPrincipal) auth.getPrincipal()).getId();
//            //List<FavoriteHomeEntity> favoriteHomeEntity = favoriteHomeRepository.findByUserIdAndHouseId(id, homeEntity.getId());
//            //boolean isLiked = favoriteHomeEntity.size() > 0;
//            //homeDto.setLiked(isLiked);
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }

       List<BillDto> bills =billRepository.findByHomeId(homeEntity.getId())
                .stream()
                .map(currentBill ->
                {
                    return new BillDto(currentBill.getId(), currentBill.getSum(),currentBill.getHome().getId(),currentBill.getBillType(), currentBill.getIssuedBy(),currentBill.getIssueDate(),currentBill.getDeadline());
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
