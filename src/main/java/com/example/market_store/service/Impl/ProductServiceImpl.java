package com.example.market_store.service.Impl;

import com.example.market_store.criteria.ProductCriteria;
import com.example.market_store.dto.requestDto.RequestProductDto;
import com.example.market_store.dto.responseDto.ResponseProductDto;
import com.example.market_store.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public Page<ResponseProductDto> findProductByCriteria(ProductCriteria productCriteria, int page, int size) {
        return null;
    }

    @Override
    public ResponseProductDto addProduct(RequestProductDto requestProductDto) {
        return null;
    }

    @Override
    public ResponseProductDto UpdateProduct(RequestProductDto requestProductDto) {
        return null;
    }

    @Override
    public void deleteProduct(long id) {

    }
}
