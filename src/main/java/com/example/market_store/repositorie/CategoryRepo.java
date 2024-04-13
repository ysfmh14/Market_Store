package com.example.market_store.repositorie;

import com.example.market_store.entitie.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Category> {
}
