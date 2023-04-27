package com.imobiliare360.service;

import com.imobiliare360.converter.BillConverter;
import com.imobiliare360.converter.HomeConverter;
import com.imobiliare360.dto.BillDto;
import com.imobiliare360.dto.HomeDto;
import com.imobiliare360.dto.RoomDto;
import com.imobiliare360.entity.*;
import com.imobiliare360.repository.*;
import com.imobiliare360.security.model.User;
import com.imobiliare360.security.repository.UserRepository;
import com.imobiliare360.util.BillStatus;
import com.imobiliare360.util.PriceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillStatusRepository billStatusRepository;

    @Autowired
    private HomeRepository homeRepository;
//    @Autowired
//    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillConverter billConverter;
    @Autowired
    private ProviderServicesRepository providerServiceRepository;
    @Autowired
    private ProviderRepository providerRepository;
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
            //billEntity.setIssuedBy(billDto.getIssuedBy());
            billEntity.setDeadline(billDto.getDeadline());
            HomeEntity home = homeRepository.getById(homeId);
            billEntity.setHome(home);
            ProviderServiceEntity providedService = providerServiceRepository.getById(billDto.getProviderService().getId());
            billEntity.setProviderService(providedService);
            BillStatusEntity status = billStatusRepository.getById(billDto.getStatus().getId());
            billEntity.setStatus(status);

            billEntity.setStatus(status);

            //homeEntity.setUser(user);

            //billEntity = billRepository.save(billEntity);
            billRepository.save(billEntity);
    }

    public void generateBillsForServiceProvider(Long serviceProviderID){


    }

    public void generateBillsForServiceProviderServiceType(Long serviceProviderID,Long serviceTypeID){
           ProviderServiceEntity pse = providerRepository.getById(serviceProviderID).getServices().stream().filter(providerServiceEntity -> {
                return providerServiceEntity.getServiceType().getId().equals(serviceTypeID);
           }).findFirst().get();

            List<HomeEntity> homes = homeRepository.findAll().stream().filter(home -> {
                return home.getServices().stream().anyMatch(serviceEntity -> {
                    return serviceEntity.getServiceType().getId().equals(serviceTypeID)
                            && serviceEntity.getProvider().getId().equals(serviceProviderID);
                });
            }).collect(Collectors.toList());

            List<BillEntity> bills = new ArrayList<>();

            for(HomeEntity home : homes){
                BillEntity newBill = new BillEntity();
                newBill.setHome(home);
                List<BillStatusEntity> statuses = billStatusRepository.findAll();
                BillStatusEntity pendingStatus = statuses.stream().filter(billStatus-> {
                    return  BillStatus.PENDING.equals(billStatus.getStatus());
                }).findFirst().get();
                BillStatusEntity expectingInputStatus = statuses.stream().filter(billStatus-> {
                    return  BillStatus.EXPECTING_INPUT.equals(billStatus.getStatus());
                }).findFirst().get();
                BillStatusEntity actualStatus;
                switch(pse.getServiceType().getPriceType()){
                    case PriceType.FIX:
                        actualStatus = pendingStatus;
                        break;
                    case PriceType.VARIABIL:
                        actualStatus = expectingInputStatus;
                        break;
                    default:
                        actualStatus = null;
                }

                newBill.setStatus(actualStatus);
                newBill.setProviderService(pse);
                float actualPrice=0;
                if(PriceType.FIX.equals(pse.getServiceType().getPriceType())){
                    actualPrice=pse.getPrice();
                }
                newBill.setSum(actualPrice);
            }

    }

    public void edit(BillDto billDto){
        BillEntity billEntity = new BillEntity();
        billEntity.setSum(billDto.getSum());
        billEntity.setIssueDate(billDto.getIssueDate());
        ProviderServiceEntity providedService = providerServiceRepository.getById(billDto.getProviderService().getId());
        billEntity.setProviderService(providedService);
        billEntity.setDeadline(billDto.getDeadline());
        //billEntity.setServiceType(billDto.getServiceType());
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
