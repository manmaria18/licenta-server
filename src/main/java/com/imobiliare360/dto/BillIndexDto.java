package com.imobiliare360.dto;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BillIndexDto {
    private float index;
    private Long billId;
}
