package com.example.market_store.repositorie;

import com.example.market_store.entitie.Order;
import com.example.market_store.entitie.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Long>, JpaSpecificationExecutor<OrderDetails> {
    Optional<OrderDetails> findByOrderDetailsCode(String orderDetailsCode);
}
