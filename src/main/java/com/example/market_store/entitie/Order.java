package com.example.market_store.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.List;

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
    @Column(name = "totalPrice")
    private double totalPrice;
    @Column(name = "dateTimeOrder")
    private LocalDateTime dateTimeOrder;

    @OneToOne
    private Delivery delivery;
    @OneToOne
    private Payment payment;
    @ManyToOne
    private Users user;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetailsList;
}
