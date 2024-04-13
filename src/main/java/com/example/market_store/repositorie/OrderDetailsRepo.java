package com.example.market_store.repositorie;

import com.example.market_store.entitie.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Long>, JpaSpecificationExecutor<OrderDetails> {
}
