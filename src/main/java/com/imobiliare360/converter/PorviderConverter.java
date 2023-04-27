package com.imobiliare360.converter;

import com.imobiliare360.dto.ProviderDto;
import com.imobiliare360.dto.ProviderServicesDto;
import com.imobiliare360.entity.ProviderEntity;
import com.imobiliare360.entity.ProviderServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PorviderConverter {
    @Autowired
    private ProviderServiceConverter providerServiceConverter;
    public ProviderDto providerEntityToDto(ProviderEntity provider) {
        ProviderDto providerDto = new ProviderDto();
        providerDto.setId(provider.getId());
        providerDto.setName(provider.getName());
        System.out.println("Provider Converter HERE:"+providerDto.getName());
        //List<ProviderServicesDto> providerServicesDtos = providerServiceConverter.providerServiceEntitysToDtos(provider.getServices());
        //providerDto.setServices(providerServicesDtos);
        return providerDto;
    }
}
