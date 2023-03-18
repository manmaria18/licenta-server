package com.imobiliare360.entity;
import com.imobiliare360.security.model.User;
import lombok.*;
//import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BillEntity extends BaseEntity{
    private float sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "home_id")
    private HomeEntity home;

    private BillType billType;
    @OneToOne
    private ProviderEntity issuedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;



}
