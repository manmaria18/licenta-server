package com.imobiliare360.dto;


import com.imobiliare360.entity.BillType;
import com.imobiliare360.entity.HomeEntity;
import com.imobiliare360.entity.ProviderEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProviderServicesDto {
    //tipul de serviciu
    private BillType billType;
    private float price;
}
