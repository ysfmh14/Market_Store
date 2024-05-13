package com.example.market_store.repositorie;

import com.example.market_store.entitie.Payment;
import com.example.market_store.entitie.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long>, JpaSpecificationExecutor<Payment> {
    Optional<Payment> findByPaymentCode(String paymentCode);
}
