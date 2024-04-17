package com.example.market_store.repositorie;

import com.example.market_store.entitie.Product;
import com.example.market_store.entitie.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductVariantRepo extends JpaRepository<ProductVariant,Long>, JpaSpecificationExecutor<ProductVariant> {
    Optional<ProductVariant> findByProductVariantCode(String productVariantCode);
}
