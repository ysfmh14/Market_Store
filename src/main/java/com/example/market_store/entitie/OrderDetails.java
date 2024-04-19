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
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "orderDetailsCode",unique = true)
    private String orderDetailsCode;
    @Column(name = "unitPrice")
    private double unitPrice;
    @Column(name = "quantity")
    private int quantity;
    @Transient
    private Long productVariantId;
    @Transient
    private Long orderId;
    @ManyToOne
    private ProductVariant productVariant;
    @ManyToOne
    private Order order;

}
