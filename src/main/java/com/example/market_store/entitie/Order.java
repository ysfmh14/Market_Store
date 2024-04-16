package com.example.market_store.entitie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ordert")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "orderCode",unique = true)
    private String orderCode;
    @Column(name = "status")
    private String status;
    @Column(name = "dateTimeOrder")
    private LocalDateTime dateTimeOrder;
    @OneToOne
    private Delivery delivery;
    @OneToOne
    private Payment payment;
    @ManyToOne
    private Users user;

}
