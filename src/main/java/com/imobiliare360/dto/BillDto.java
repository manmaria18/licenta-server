package com.imobiliare360.dto;


import com.imobiliare360.entity.ProviderEntity;
import com.imobiliare360.entity.ServiceType;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BillDto {
    private Long id;
    private float sum;
    private Long houseId;
    private ProviderServicesDto providerService;
    private Date issueDate;
    private Date deadline;
    private BillStatusDto status;


}
