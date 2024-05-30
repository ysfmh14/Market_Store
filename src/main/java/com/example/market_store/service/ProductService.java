package com.example.market_store.service;

import com.example.market_store.criteria.ProductCriteria;
import com.example.market_store.criteria.ProductVariantCriteria;
import com.example.market_store.dto.requestDto.RequestProductDto;
import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import com.example.market_store.dto.responseDto.ResponseProductDto;
import com.example.market_store.dto.responseDto.ResponseProductVariantDto;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<ResponseProductDto> findProductByCriteria(ProductCriteria productCriteria, int page , int size);
    ResponseProductDto addProduct(RequestProductDto requestProductDto);
    ResponseProductDto UpdateProduct(RequestProductDto requestProductDto);
    void  deleteProduct(String code);
}
