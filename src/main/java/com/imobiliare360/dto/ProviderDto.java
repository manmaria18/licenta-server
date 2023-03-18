package com.imobiliare360.dto;


import com.imobiliare360.entity.BillType;
import com.imobiliare360.entity.HomeEntity;
import com.imobiliare360.entity.ProviderEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProviderDto {
    private String name;
    private List<ProviderServicesDto> services;
}
