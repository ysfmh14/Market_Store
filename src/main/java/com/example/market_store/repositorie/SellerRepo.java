package com.example.market_store.repositorie;
import com.example.market_store.entitie.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepo extends JpaRepository<Seller,Long>, JpaSpecificationExecutor<Seller> {
    Optional<Seller> findBySellerCode(String sellerCode);
}
