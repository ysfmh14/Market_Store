package com.example.market_store.entitie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Deliveryman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "deliverymanCode",unique = true)
    private String deliverymanCode;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "phone",length = 10)
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "rib",length = 24)
    private String rib;
    @Column(name = "isActive")
    private boolean isActive;
    @OneToMany(mappedBy = "deliveryman", fetch = FetchType.LAZY)
    private List<Delivery> deliveries;
}
