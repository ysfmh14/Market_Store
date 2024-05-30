package com.example.market_store.service.Impl;

import com.example.market_store.dto.responseDto.*;
import com.example.market_store.repositorie.OrderRepo;
import com.example.market_store.repositorie.ProductRepo;
import com.example.market_store.repositorie.SellerRepo;
import com.example.market_store.repositorie.UsersRepo;
import com.example.market_store.service.SellerService;
import com.example.market_store.service.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private OrderRepo orderRepo;
    private UsersRepo usersRepo;
    private SellerRepo sellerRepo;
    private ProductRepo productRepo;
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

    @Override
    public ResponseCountProductBymonth countProductsForEachMonth() {
        List<Long> counts = new ArrayList<>();
        LocalDate now = LocalDate.now();
        ResponseCountProductBymonth responseCountProductBymonth = new ResponseCountProductBymonth();
        int currentYear = now.getYear();
        for (int month = 1; month <= 12; month++) {
            counts.add(productRepo.countByMonth(month, currentYear));
        }
        responseCountProductBymonth.setNumberOfProductsByMonths(counts);
        return responseCountProductBymonth;
    }
}
