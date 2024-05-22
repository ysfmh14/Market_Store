package com.example.market_store.service;

import com.example.market_store.dto.responseDto.ResponseCountOrdersByStatus;
import com.example.market_store.dto.responseDto.ResponseOrdersCount;
import com.example.market_store.dto.responseDto.ResponseSellersCount;
import com.example.market_store.dto.responseDto.ResponseUsersCount;

import java.util.Map;

public interface StatisticService {
    public ResponseOrdersCount countOrders();
    public ResponseUsersCount countUsers();
    public ResponseSellersCount countSellers();
    public ResponseCountOrdersByStatus getCountByStatus();
}
