package com.example.market_store.repositorie;

import com.example.market_store.entitie.Order;
import com.example.market_store.entitie.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long>, JpaSpecificationExecutor<Order> {
    Optional<Order> findByOrderCode(String orderCode);
    @Query("SELECT o.status, COUNT(o) FROM Order o GROUP BY o.status")
    List<Object[]> countOrdersByStatus();

}
