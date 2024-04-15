package com.example.market_store.service;

import com.example.market_store.criteria.ArticleOpinionCriteria;
import com.example.market_store.criteria.CategoryCriteria;
import com.example.market_store.dto.requestDto.RequestArticleOpinionDto;
import com.example.market_store.dto.requestDto.RequestCategoryDto;
import com.example.market_store.dto.responseDto.ResponseArticleOpinionDto;
import com.example.market_store.dto.responseDto.ResponseCategoryDto;
import org.springframework.data.domain.Page;

public interface ArticleOpinionService {
    Page<ResponseArticleOpinionDto> findArticleOpinionByCriteria(ArticleOpinionCriteria articleOpinionCriteria, int page , int size);
    ResponseArticleOpinionDto addArticleOpinion(RequestArticleOpinionDto requestArticleOpinionDto);
    ResponseArticleOpinionDto UpdateArticleOpinion(RequestArticleOpinionDto requestArticleOpinionDto);
    void  deleteArticleOpinion(long id);
}
