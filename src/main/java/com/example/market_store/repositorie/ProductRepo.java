package com.example.market_store.repositorie;

import com.example.market_store.entitie.Product;
import com.example.market_store.entitie.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByProductCode(String productCode);
}
