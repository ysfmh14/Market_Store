package com.example.market_store.service.Impl;

import com.example.market_store.dto.responseDto.ResponseCountOrdersByStatus;
import com.example.market_store.dto.responseDto.ResponseOrdersCount;
import com.example.market_store.dto.responseDto.ResponseSellersCount;
import com.example.market_store.dto.responseDto.ResponseUsersCount;
import com.example.market_store.repositorie.OrderRepo;
import com.example.market_store.repositorie.SellerRepo;
import com.example.market_store.repositorie.UsersRepo;
import com.example.market_store.service.SellerService;
import com.example.market_store.service.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private OrderRepo orderRepo;
    private UsersRepo usersRepo;
    private SellerRepo sellerRepo;
    @Override
    public ResponseOrdersCount countOrders() {
        ResponseOrdersCount responseOrdersCount = new ResponseOrdersCount();
        responseOrdersCount.setNumberOfOrders(orderRepo.count());
        return responseOrdersCount;
    }

    @Override
    public ResponseUsersCount countUsers() {
        ResponseUsersCount responseUsersCount = new ResponseUsersCount();
        responseUsersCount.setNumberOfUsers(usersRepo.count());
        return responseUsersCount;
    }

    @Override
    public ResponseSellersCount countSellers() {
        ResponseSellersCount responseSellersCount = new ResponseSellersCount();
        responseSellersCount.setNumberOfSellers(sellerRepo.count());
        return responseSellersCount;
    }

    @Override
    public ResponseCountOrdersByStatus getCountByStatus() {
        List<Object[]> results = orderRepo.countOrdersByStatus();
        Map<String, Long> counts =results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Long) result[1]
                ));
        ResponseCountOrdersByStatus responseCountOrdersByStatus = new ResponseCountOrdersByStatus();
        responseCountOrdersByStatus.setOrdersDelivered(counts.get("Delivered"));
        responseCountOrdersByStatus.setOrdersShipped(counts.get("Shipped"));
        responseCountOrdersByStatus.setOrdersInProgress(counts.get("In progress"));
        return responseCountOrdersByStatus;
    }
}
