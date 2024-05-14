package com.example.market_store.repositorie;

import com.example.market_store.entitie.Category;
import com.example.market_store.entitie.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Category> {
    Optional<Category> findByCategoryCode(String categoryCode);
    void deleteByCategoryCode(String categoryCode);
}
