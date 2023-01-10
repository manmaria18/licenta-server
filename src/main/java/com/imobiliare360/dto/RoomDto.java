package com.imobiliare360.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoomDto {
    private byte[] imageData;
    private byte[] audioData;
}
