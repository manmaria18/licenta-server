package com.imobiliare360.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FavoritesListDto {
    private Long userId;
    private List<HomeDto> favoriteHomesList;
}
