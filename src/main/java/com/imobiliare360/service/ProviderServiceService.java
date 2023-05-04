package com.imobiliare360.service;

import com.imobiliare360.converter.ProviderServiceConverter;
import com.imobiliare360.converter.ServiceTypeConverter;
import com.imobiliare360.dto.ProviderDto;
import com.imobiliare360.dto.ProviderServicesDto;
import com.imobiliare360.dto.ServiceTypeDto;
import com.imobiliare360.entity.HomeEntity;
import com.imobiliare360.entity.ProviderEntity;
import com.imobiliare360.entity.ProviderServiceEntity;
import com.imobiliare360.entity.ServiceType;
import com.imobiliare360.repository.ProviderRepository;
import com.imobiliare360.repository.ProviderServicesRepository;
import com.imobiliare360.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderServiceService {
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    ProviderServicesRepository providerServicesRepository;

    @Autowired
    ServiceTypeConverter serviceTypeConverter;

    @Autowired
    ProviderServiceConverter providerServiceConverter;
    @Autowired
    ServiceTypeRepository serviceTypeRepository;
    public void save(ProviderServicesDto providerServiceDto) {

        ProviderServiceEntity providerServiceEntity = new ProviderServiceEntity();
        ServiceType serviceTypeEntity = serviceTypeRepository.getById(providerServiceDto.getServiceType().getId());
        providerServiceEntity.setServiceType(serviceTypeEntity);
        providerServiceEntity.setPrice(providerServiceDto.getPrice());
        ProviderEntity providerEntity = providerRepository.getById(providerServiceDto.getProvider().getId());
        providerServiceEntity.setProvider(providerEntity);
        providerServicesRepository.save(providerServiceEntity);
    }

    public List<ProviderServicesDto> getAll() {
        List<ProviderServiceEntity> servicesEntities = providerServicesRepository.findAll();

        //System.out.println("SERVICE getAllServices: "+servicesEntities.toString());
        return providerServiceConverter.providerServicesEntitiesToDtos(servicesEntities);
    }

    public void save(ServiceTypeDto serviceTypeDto){
       ServiceType serviceType = new ServiceType();
       serviceType.setType(serviceTypeDto.getType());
       serviceType.setPriceType(serviceTypeDto.getPriceType());
       serviceTypeRepository.save(serviceType);
    }
}
