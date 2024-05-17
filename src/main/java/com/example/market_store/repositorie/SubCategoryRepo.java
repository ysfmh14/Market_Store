package com.example.market_store.repositorie;

import com.example.market_store.entitie.Category;
import com.example.market_store.entitie.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory,Long>, JpaSpecificationExecutor<SubCategory> {
    Optional<SubCategory> findBySubCategoryCode(String subCategoryCode);
    void deleteBySubCategoryCode(String subCategoryCode);
}
