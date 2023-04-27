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

    public void save(ProviderDto providerDto) {

        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setName(providerDto.getName());
        List<ProviderServiceEntity> psEntities = new ArrayList<>();
        // go through the services

        providerEntity.setServices(psEntities);
        providerRepository.save(providerEntity);
    }
}
