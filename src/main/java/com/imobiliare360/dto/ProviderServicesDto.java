package com.imobiliare360.dto;


import com.imobiliare360.entity.ServiceType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProviderServicesDto {
    private long id;
    //tipul de serviciu
    private ServiceTypeDto serviceType;
    private float price;
    private ProviderDto provider;
}
