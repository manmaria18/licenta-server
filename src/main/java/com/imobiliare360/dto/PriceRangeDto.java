package com.imobiliare360.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PriceRangeDto
{
    private float top;
    private float bottom;
}
