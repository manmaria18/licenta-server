package com.imobiliare360.entity;
import lombok.*;
//import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="provider")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProviderEntity extends BaseEntity{
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProviderServiceEntity> services;


}
