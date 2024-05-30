package com.example.market_store.Controller;

import com.example.market_store.dto.responseDto.*;
import com.example.market_store.service.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/statistics")
@CrossOrigin("*")
public class StatisticController {
    private StatisticService statisticService;
    @GetMapping("/orders")
//    @PreAuthorize("hasRole('admin')")
    public ResponseOrdersCount getOrdersCount(){

        return statisticService.countOrders();
    }
    @GetMapping("/users")
//    @PreAuthorize("hasRole('admin')")
    public ResponseUsersCount getUsersCount(){

        return statisticService.countUsers();
    }
    @GetMapping("/sellers")
//    @PreAuthorize("hasRole('admin')")
    public ResponseSellersCount getSellersCount(){

        return statisticService.countSellers();
    }
    @GetMapping("/countOrdersByStatus")
//    @PreAuthorize("hasRole('admin')")
    public ResponseCountOrdersByStatus getCountOrdersByStatus(){
        return statisticService.getCountByStatus();
    }
    @GetMapping("/countProductsByMonth")
//    @PreAuthorize("hasRole('admin')")
    public ResponseCountProductBymonth getCountProductsByMonth(){
        return statisticService.countProductsForEachMonth();
    }
}
