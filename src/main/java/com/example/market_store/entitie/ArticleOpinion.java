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
public class ArticleOpinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "opinionCode",unique = true)
    private String opinionCode;
    @Column(name = "opinion")
    private String opinion;
    @Transient
    private Long userId;
    @Transient
    private Long productId;
    @ManyToOne
    private Users user;
    @ManyToOne
    private Product product;
}
