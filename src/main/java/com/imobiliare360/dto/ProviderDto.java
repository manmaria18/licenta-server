package com.imobiliare360.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProviderDto {
    private Long id;
    private String name;
    private List<ProviderServicesDto> services;
}
