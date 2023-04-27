package com.imobiliare360.entity;

import com.imobiliare360.security.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="home")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HomeEntity extends BaseEntity
{
    private String name;
    //private String description;
    //private float price;


//    @OneToMany(mappedBy="home", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<BillEntity> bills;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private LocationEntity location;

    @ManyToOne
    private User user;

    @OneToMany
    private List<ProviderServiceEntity> services;
}
