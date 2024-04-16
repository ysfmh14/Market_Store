package com.example.market_store.entitie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "deliveryCode",unique = true)
    private String deliveryCode;
    @Column(name = "deliveryStatus")
    private String deliveryStatus;
    @Column(name = "deliveryAddress")
    private String deliveryAddress;
    @Column(name = "deliveryDate")
    private String deliveryDate;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Deliveryman deliveryman;


}
