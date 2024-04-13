package com.example.market_store.repositorie;

import com.example.market_store.entitie.Deliveryman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverymanRepo extends JpaRepository<Deliveryman,Long>, JpaSpecificationExecutor<Deliveryman> {
}
