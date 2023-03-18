package com.imobiliare360.entity;
import lombok.*;
//import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;

@Entity
@Table(name="provided_service")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class ProviderServiceEntity extends BaseEntity{
    //tipul de serviciu
    private BillType billType;
    private float price;
}
