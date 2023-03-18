package com.imobiliare360.entity;
import lombok.*;
//import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name="service")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class ProviderServiceEntity extends BaseEntity{
    //tipul de serviciu
    @OneToOne
    private ServiceType serviceType;
    private float price;
   // private List<HouseEntity>
    @ManyToOne
    private ProviderEntity provider;
}
