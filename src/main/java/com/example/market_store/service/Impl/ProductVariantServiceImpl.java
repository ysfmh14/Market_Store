package com.example.market_store.service.Impl;

import com.example.market_store.criteria.ProductVariantCriteria;
import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import com.example.market_store.dto.responseDto.ResponseProductVariantDto;
import com.example.market_store.service.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {
    @Override
    public Page<ResponseProductVariantDto> findProductVariantByCriteria(ProductVariantCriteria productVariantCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponseProductVariantDto addProductVariant(RequestProductVariantDto requestProductVariantDto) {
        return null;
    }

    @Override
    public ResponseProductVariantDto UpdateProductVariant(RequestProductVariantDto requestProductVariantDto) {
        return null;
    }

    @Override
    public void deleteProductVariant(long id) {

    }
}
