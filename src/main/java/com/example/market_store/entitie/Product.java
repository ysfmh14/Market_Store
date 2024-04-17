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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productCode",unique = true)
    private String productCode;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ElementCollection
    @Column(name = "imageUrl")
    private List<String> imagesUrl;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "unitPrice")
    private double unitPrice;
    @Column(name = "available")
    private boolean available;
    @Transient
    private Long sellerId;
    @Transient
    private Long subCategoryId;
    @ManyToOne
    private Seller seller;
    @ManyToOne
    private SubCategory subCategory;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProductVariant> productVariants;




}
