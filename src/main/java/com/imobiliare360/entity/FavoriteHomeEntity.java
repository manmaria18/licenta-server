package com.imobiliare360.entity;

import com.imobiliare360.security.model.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="favorite_home")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FavoriteHomeEntity extends BaseEntity {

    @ManyToOne
    User user;

    @ManyToOne
    HomeEntity home;
}


