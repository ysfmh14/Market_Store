package com.example.market_store.repositorie;

import com.example.market_store.entitie.Product;
import com.example.market_store.entitie.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByProductCode(String productCode);
    @Query("SELECT COUNT(p) FROM Product p WHERE MONTH(p.createDate) = :month AND YEAR(p.createDate) = :year")
    Long countByMonth(@Param("month") int month, @Param("year") int year);
    void deleteByProductCode(String productCode);
}
