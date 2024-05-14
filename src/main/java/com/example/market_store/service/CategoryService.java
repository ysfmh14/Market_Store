package com.example.market_store.service;

import com.example.market_store.criteria.CategoryCriteria;
import com.example.market_store.criteria.DeliveryCriteria;
import com.example.market_store.dto.requestDto.RequestCategoryDto;
import com.example.market_store.dto.requestDto.RequestDeliveryDto;
import com.example.market_store.dto.responseDto.ResponseCategoryDto;
import com.example.market_store.dto.responseDto.ResponseDeliveryDto;
import org.springframework.data.domain.Page;

public interface CategoryService {
    Page<ResponseCategoryDto> findCategoryByCriteria(CategoryCriteria categoryCriteria, int page , int size);
    ResponseCategoryDto addCategory(RequestCategoryDto requestCategoryDto);
    ResponseCategoryDto UpdateCategory(RequestCategoryDto requestCategoryDto);
    void  deleteCategory(String categoryCode);
}
