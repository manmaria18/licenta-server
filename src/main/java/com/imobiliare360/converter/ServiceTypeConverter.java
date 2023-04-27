package com.imobiliare360.converter;

import com.imobiliare360.dto.ServiceTypeDto;
import com.imobiliare360.entity.ServiceType;
import org.springframework.stereotype.Component;

@Component
public class ServiceTypeConverter {
    public ServiceTypeDto serviceTypeEntityToDto(ServiceType serviceType) {
        ServiceTypeDto stDto = new ServiceTypeDto();
        stDto.setId(serviceType.getId());
        stDto.setType(serviceType.getType());
        return stDto;
    }
}
