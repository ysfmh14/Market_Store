package com.example.market_store.service;

import com.example.market_store.dto.responseDto.*;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    public ResponseOrdersCount countOrders();
    public ResponseUsersCount countUsers();
    public ResponseSellersCount countSellers();
    public ResponseCountOrdersByStatus getCountByStatus();
    public ResponseCountProductBymonth countProductsForEachMonth();
}
