package com.example.market_store.service;

import com.example.market_store.criteria.SellerCriteria;
import com.example.market_store.dto.requestDto.RequestSellerDto;
import com.example.market_store.dto.responseDto.ResponseSellerDto;
import org.springframework.data.domain.Page;

public interface SellerService {

    Page<ResponseSellerDto> findSellerByCriteria(SellerCriteria sellerCriteria, int page , int size);
    ResponseSellerDto addSeller(RequestSellerDto requestSellerDto);
    ResponseSellerDto UpdateSeller(RequestSellerDto requestSellerDto);
    void  deleteSeller(long id);
}
