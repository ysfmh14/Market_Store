package com.example.market_store.service;

import com.example.market_store.criteria.SellerCriteria;
import com.example.market_store.criteria.SubCategoryCriteria;
import com.example.market_store.dto.requestDto.RequestSellerDto;
import com.example.market_store.dto.requestDto.RequestSubCategoryDto;
import com.example.market_store.dto.responseDto.ResponseSellerDto;
import com.example.market_store.dto.responseDto.ResponseSubCategoryDto;
import org.springframework.data.domain.Page;

public interface SubCategoryService {
    Page<ResponseSubCategoryDto> findSubCategoryByCriteria(SubCategoryCriteria subCategoryCriteria, int page , int size);
    ResponseSubCategoryDto addSubCategory(RequestSubCategoryDto requestSubCategoryDto);
    ResponseSubCategoryDto UpdateSubCategory(RequestSubCategoryDto requestSubCategoryDto);
    void  deleteSubCategory(long id);
}
