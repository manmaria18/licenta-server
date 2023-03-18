package com.imobiliare360.service;
import com.imobiliare360.dto.*;
import com.imobiliare360.entity.*;
import com.imobiliare360.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    ProviderServicesRepository providerServicesRepository;

    public void save(ProviderDto providerDto, Long homeId) {


        //homeDto.setRooms( new ArrayList<>());

//            for(int i=0; i < images.size(); i++)
//            {
//                RoomDto roomDto = new RoomDto();
//                roomDto.setImageData(images.get(i).getBytes());
//
//                homeDto.getRooms().add(roomDto);
//
//            }

        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setName(providerDto.getName());
        List<ProviderServiceEntity> psEntities = new ArrayList<>();
        // go through the services
        for(ProviderServicesDto psDto : providerDto.getServices())
        {

            ProviderServiceEntity psEntity = new ProviderServiceEntity();
            psEntity.setBillType(psDto.getBillType());
            psEntity.setPrice(psDto.getPrice());


            psEntity = providerServicesRepository.save(psEntity);

            psEntities.add(psEntity);

        }
        providerEntity.setServices(psEntities);
        //User user = userRepository.getById(userId);
        //homeEntity.setUser(user);

        //billEntity = billRepository.save(billEntity);
        providerRepository.save(providerEntity);
    }
}
