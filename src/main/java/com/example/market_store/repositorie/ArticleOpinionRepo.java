package com.example.market_store.repositorie;

import com.example.market_store.entitie.ArticleOpinion;
import com.example.market_store.entitie.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleOpinionRepo extends JpaRepository<ArticleOpinion,Long>, JpaSpecificationExecutor<ArticleOpinion> {
    Optional<ArticleOpinion> findByOpinionCode(String articleOpinionCode);
}
