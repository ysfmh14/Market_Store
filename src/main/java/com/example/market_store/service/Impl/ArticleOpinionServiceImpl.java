package com.example.market_store.service.Impl;

import com.example.market_store.criteria.ArticleOpinionCriteria;
import com.example.market_store.dto.requestDto.RequestArticleOpinionDto;
import com.example.market_store.dto.responseDto.ResponseArticleOpinionDto;
import com.example.market_store.service.ArticleOpinionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleOpinionServiceImpl implements ArticleOpinionService {
    @Override
    public Page<ResponseArticleOpinionDto> findArticleOpinionByCriteria(ArticleOpinionCriteria articleOpinionCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponseArticleOpinionDto addArticleOpinion(RequestArticleOpinionDto requestArticleOpinionDto) {
        return null;
    }

    @Override
    public ResponseArticleOpinionDto UpdateArticleOpinion(RequestArticleOpinionDto requestArticleOpinionDto) {
        return null;
    }

    @Override
    public void deleteArticleOpinion(long id) {

    }
}
