package com.example.market_store.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    @Column(name = "createDate")
    private LocalDate createDate;
    @Column(name = "available")
    private boolean available;
    @Transient
    private String sellerCode;
    @Transient
    private String subCategoryCode;
    @ManyToOne
    private Seller seller;
    @ManyToOne
    private SubCategory subCategory;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ProductVariant> productVariants;




}
