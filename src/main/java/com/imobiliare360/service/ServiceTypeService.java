package com.imobiliare360.service;

import com.imobiliare360.converter.BillConverter;
import com.imobiliare360.dto.BillDto;
import com.imobiliare360.dto.BillIndexDto;
import com.imobiliare360.dto.BillStatusDto;
import com.imobiliare360.dto.ServiceTypeDto;
import com.imobiliare360.entity.*;
import com.imobiliare360.repository.*;
import com.imobiliare360.security.repository.UserRepository;
import com.imobiliare360.util.BillStatus;
import com.imobiliare360.util.PriceTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceTypeService {

    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeDto findByType(String type) {
        ServiceType serviceType = serviceTypeRepository.findByType(type);
        ServiceTypeDto serviceTypeDto = new ServiceTypeDto();
        serviceTypeDto.setType(serviceType.getType());
        serviceTypeDto.setPriceType(serviceType.getPriceType());
        serviceTypeDto.setId(serviceType.getId());

        return serviceTypeDto;
    }

    public void save(ServiceTypeDto serviceTypeDto) {
        ServiceType serviceType = new ServiceType();
        serviceType.setType(serviceTypeDto.getType());
        serviceType.setPriceType(serviceTypeDto.getPriceType());
        serviceTypeRepository.save(serviceType);
    }
}
