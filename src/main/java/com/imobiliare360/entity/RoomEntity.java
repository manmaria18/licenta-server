package com.imobiliare360.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="room")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoomEntity extends BaseEntity
{
    @Lob
    @Column(name = "image")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] imageData;

    @Lob
    @Column(name = "audio")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] audioData;

    @ManyToOne
    private HomeEntity homeEntity;

}
