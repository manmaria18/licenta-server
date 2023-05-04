package com.imobiliare360.dto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ServiceTypeDto {
    Long id;
    String type;
    String priceType;
}
