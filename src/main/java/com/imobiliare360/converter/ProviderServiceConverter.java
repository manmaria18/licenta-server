package com.imobiliare360.converter;

import com.imobiliare360.dto.ProviderDto;
import com.imobiliare360.dto.ProviderServicesDto;
import com.imobiliare360.dto.ServiceTypeDto;
import com.imobiliare360.entity.ProviderServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProviderServiceConverter {

    @Autowired
    private PorviderConverter providerConverter;
    @Autowired
    private ServiceTypeConverter serviceTypeConverter;

    public ProviderServicesDto convertToDto(ProviderServiceEntity providerServiceEntity) {
        System.out.println("Entered in convertToDto");
        ProviderServicesDto psDto = new ProviderServicesDto();
        psDto.setId(providerServiceEntity.getId());
        System.out.println(providerServiceEntity.getProvider().toString());
        ProviderDto providerDto = providerConverter.providerEntityToDto(providerServiceEntity.getProvider());//new ProviderDto(providerServiceEntity.getProvider().getId(),providerServiceEntity.getProvider().getName());//
        System.out.println("Provider:"+providerDto.toString());
        psDto.setProvider(providerDto);
        psDto.setPrice(providerServiceEntity.getPrice());
        ServiceTypeDto stDto = new ServiceTypeDto(providerServiceEntity.getServiceType().getId(), providerServiceEntity.getServiceType().getType());//serviceTypeConverter.serviceTypeEntityToDto(providerServiceEntity.getServiceType());
        System.out.println("ServiceType:"+stDto.toString());
        psDto.setServiceType(stDto);
        return psDto;
    }

    public List<ProviderServicesDto> providerServicesEntitiesToDtos(List<ProviderServiceEntity> services) {
        return services.stream()
                .map(currentService -> convertToDto(currentService)
                )
                .collect(Collectors.toList());
    }
}
