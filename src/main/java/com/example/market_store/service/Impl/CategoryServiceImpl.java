package com.example.market_store.service.Impl;

import com.example.market_store.criteria.CategoryCriteria;
import com.example.market_store.dto.requestDto.RequestCategoryDto;
import com.example.market_store.dto.responseDto.ResponseCategoryDto;
import com.example.market_store.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Override
    public Page<ResponseCategoryDto> findCategoryByCriteria(CategoryCriteria categoryCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponseCategoryDto addCategory(RequestCategoryDto requestCategoryDto) {
        return null;
    }

    @Override
    public ResponseCategoryDto UpdateCategory(RequestCategoryDto requestCategoryDto) {
        return null;
    }

    @Override
    public void deleteCategory(long id) {

    }
}
