package com.imobiliare360.service;

import com.imobiliare360.converter.BillConverter;
import com.imobiliare360.converter.HomeConverter;
import com.imobiliare360.dto.BillDto;
import com.imobiliare360.dto.HomeDto;
import com.imobiliare360.dto.RoomDto;
import com.imobiliare360.entity.BillEntity;
import com.imobiliare360.entity.HomeEntity;
import com.imobiliare360.entity.LocationEntity;
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
import java.util.stream.Collectors;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private HomeRepository homeRepository;
//    @Autowired
//    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillConverter billConverter;
//    @Autowired
//    private FavoriteHomeRepository favoriteHomeRepository;
    public List<BillDto> search(Long houseId) {
        List<BillEntity> billEntities = billRepository.findByHomeId(houseId);
        return billConverter.billEntitiesToDtos(billEntities);
    }

//    public List<BillDto> getAllForUser(Long id) {
//        List<BillEntity> billEntities = billRepository.findByHomeId(id);
//
//        return billConverter.billEntitiesToDtos(billEntities);
//    }

    public List<BillDto> getAll() {
        List<BillEntity> billEntities = billRepository.findAll();

        return billConverter.billEntitiesToDtos(billEntities);
    }

    public BillDto get(Long idNumeric) {
        BillEntity billEntity = billRepository.getById(idNumeric);

        return billConverter.billEntityToDto(billEntity);
    }

    public BillDto delete(Long idNumeric) {
        //favoriteBillRepository.deleteByHomeId(idNumeric);

        BillEntity billEntity = billRepository.getById(idNumeric);
        billRepository.delete(billEntity);
        //billRepository.deleteById(idNumeric);
        BillDto billDto = new BillDto();
        billDto.setId(billEntity.getId());

        return billDto;
    }

    public void save(BillDto billDto, Long homeId) {


            //homeDto.setRooms( new ArrayList<>());

//            for(int i=0; i < images.size(); i++)
//            {
//                RoomDto roomDto = new RoomDto();
//                roomDto.setImageData(images.get(i).getBytes());
//
//                homeDto.getRooms().add(roomDto);
//
//            }

            BillEntity billEntity = new BillEntity();
            billEntity.setSum(billDto.getSum());
            billEntity.setIssueDate(billDto.getIssueDate());
            billEntity.setIssuedBy(billDto.getIssuedBy());
            billEntity.setDeadline(billDto.getDeadline());
            HomeEntity home = homeRepository.getById(homeId);
            billEntity.setHome(home);
            billEntity.setBillType(billDto.getBillType());
            //User user = userRepository.getById(userId);
            //homeEntity.setUser(user);

            //billEntity = billRepository.save(billEntity);
            billRepository.save(billEntity);
    }

    public void edit(BillDto billDto){
        BillEntity billEntity = new BillEntity();
        billEntity.setSum(billDto.getSum());
        billEntity.setIssueDate(billDto.getIssueDate());
        billEntity.setIssuedBy(billDto.getIssuedBy());
        billEntity.setDeadline(billDto.getDeadline());
        billEntity.setBillType(billDto.getBillType());
        //HomeEntity home = homeRepository.getById(homeId);
        //billEntity.setHouse(bill.getHouse());
        //User user = userRepository.getById(userId);
        //homeEntity.setUser(user);

        //billEntity = billRepository.save(billEntity);
        billRepository.save(billEntity);
    }

    public List<BillDto> getAllForUser(Long id) {
        //User user = userRepository.getById(id);
        List<BillEntity> bills = homeRepository.findByUserId(id)
                .stream()
                .map(home -> home.getId())
                .flatMap(homeId -> billRepository.findByHomeId(homeId).stream())
                .collect(Collectors.toList());
        List<BillDto> billsOfAUser = new ArrayList<>();
        return billsOfAUser;
    }
}
