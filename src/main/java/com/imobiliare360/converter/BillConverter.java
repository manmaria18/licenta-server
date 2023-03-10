package com.imobiliare360.converter;

import com.imobiliare360.dto.BillDto;
import com.imobiliare360.dto.HomeDto;
import com.imobiliare360.dto.RoomDto;
import com.imobiliare360.entity.BillEntity;
import com.imobiliare360.entity.FavoriteHomeEntity;
import com.imobiliare360.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BillConverter {
    private HomeConverter homeConverter;
    public List<BillDto> billEntitiesToDtos(List<BillEntity> billEntities) {
        return billEntities.stream()
                .map(currentBill -> billEntityToDto(currentBill)
                )
                .collect(Collectors.toList());
    }

    public BillDto billEntityToDto(BillEntity billEntity) {
        BillDto billDto = new BillDto();
        billDto.setId(billEntity.getId());
        billDto.setSum(billEntity.getSum());
        billDto.setIssueDate(billEntity.getIssueDate());
        billDto.setIssuedBy(billEntity.getIssuedBy());
        billDto.setDeadline(billEntity.getDeadline());
        HomeDto house = homeConverter.homeEntityToDto(billEntity.getHome());
        billDto.setHouseId(house.getId());
        //homeDto.setLocation(locationEntityToDto(homeEntity.getLocation()));
        //homeDto.setUserDto(userEntityToDto(homeEntity.getUser()));

        //homeDto.setLiked(false);
//        try {
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//            long id = ((UserPrincipal) auth.getPrincipal()).getId();
//            List<FavoriteHomeEntity> favoriteHomeEntity = favoriteHomeRepository.findByUserIdAndHouseId(id, homeEntity.getId());
//            boolean isLiked = favoriteHomeEntity.size() > 0;
//            homeDto.setLiked(isLiked);
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }

//        List<RoomDto> rooms = homeEntity.getRooms().stream()
//                .map(currentRoom ->
//                {
//                    return new RoomDto(currentRoom.getImageData(), currentRoom.getAudioData());
//                })
//                .collect(Collectors.toList());
//
//        homeDto.setRooms(rooms);

        return billDto;
    }
}
