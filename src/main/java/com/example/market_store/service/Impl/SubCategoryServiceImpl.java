package com.example.market_store.service.Impl;

import com.example.market_store.criteria.SubCategoryCriteria;
import com.example.market_store.dto.requestDto.RequestSubCategoryDto;
import com.example.market_store.dto.responseDto.ResponseSubCategoryDto;
import com.example.market_store.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    @Override
    public Page<ResponseSubCategoryDto> findSubCategoryByCriteria(SubCategoryCriteria subCategoryCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponseSubCategoryDto addSubCategory(RequestSubCategoryDto requestSubCategoryDto) {
        return null;
    }

    @Override
    public ResponseSubCategoryDto UpdateSubCategory(RequestSubCategoryDto requestSubCategoryDto) {
        return null;
    }

    @Override
    public void deleteSubCategory(long id) {

    }
}
