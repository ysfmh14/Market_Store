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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userCode",unique = true)
    private String userCode;
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
    @Transient
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

}
