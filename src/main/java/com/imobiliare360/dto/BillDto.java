package com.imobiliare360.dto;


import com.imobiliare360.entity.BillType;
import com.imobiliare360.entity.HomeEntity;
import lombok.*;

import java.time.LocalDate;
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
    private BillType billType;
    private String issuedBy;
    private Date issueDate;
    private Date deadline;
}
