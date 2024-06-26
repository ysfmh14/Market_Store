package com.example.market_store.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sellerCode",unique = true)
    private String sellerCode;
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
    @Column(name = "address")
    private String address;
    @Column(name = "rib",length = 24)
    private String rib;
    @Column(name = "isActive")
    private boolean isActive;
    @Transient
    private String password;
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Product> products;
//    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Product> productList;


}
