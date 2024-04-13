package com.example.market_store.repositorie;

import com.example.market_store.entitie.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory,Long>, JpaSpecificationExecutor<SubCategory> {
}
