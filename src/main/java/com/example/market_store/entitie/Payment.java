package com.example.market_store.entitie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "paymentCode",unique = true)
    private String paymentCode;
    @Column(name = "paymentMode")
    private String paymentMode;
    @Column(name = "amount")
    private String amount;
    @Column(name = "dateTimePayment")
    private LocalDateTime dateTimePayment;
    @OneToOne
    private Order order;
    @OneToOne
    private Invoice invoice;

}
