package com.example.market_store.service;

import com.example.market_store.criteria.ProductVariantCriteria;
import com.example.market_store.criteria.SellerCriteria;
import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import com.example.market_store.dto.requestDto.RequestSellerDto;
import com.example.market_store.dto.responseDto.ResponseProductVariantDto;
import com.example.market_store.dto.responseDto.ResponseSellerDto;
import org.springframework.data.domain.Page;

public interface ProductVariantService {
    Page<ResponseProductVariantDto> findProductVariantByCriteria(ProductVariantCriteria productVariantCriteria, int page , int size);
    ResponseProductVariantDto addProductVariant(RequestProductVariantDto requestProductVariantDto);
    ResponseProductVariantDto UpdateProductVariant(RequestProductVariantDto requestProductVariantDto);
    void  deleteProductVariant(long id);
}
