package com.example.market_store.Controller;

import com.example.market_store.criteria.OrderCriteria;
import com.example.market_store.criteria.SubCategoryCriteria;
import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.requestDto.RequestSubCategoryDto;
import com.example.market_store.dto.responseDto.ResponseOrderDto;
import com.example.market_store.dto.responseDto.ResponseSubCategoryDto;
import com.example.market_store.service.OrderService;
import com.example.market_store.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    @GetMapping
    Page<ResponseOrderDto> getOrderByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                    @RequestParam(defaultValue = "10" , name = "size") int size,
                                                    @RequestParam( name = "id", required = false) Long id ,
                                                    @RequestParam(name = "status", required = false) String status ,
                                                    @RequestParam(name = "userId", required = false) Long userId ,
                                                    @RequestParam(name = "orderCode", required = false) String orderCode){

        OrderCriteria orderCriteria = new OrderCriteria();
        orderCriteria.setId(id);
        orderCriteria.setStatus(status);
        orderCriteria.setUserId(userId);
        orderCriteria.setOrderCode(orderCode);
        return orderService.findOrderByCriteria(orderCriteria,page,size);
    }
    @PostMapping
    public ResponseOrderDto save(@RequestBody RequestOrderDto requestOrderDto){
        return orderService.addOrder(requestOrderDto);
    }
    @PutMapping
    public ResponseOrderDto update(@RequestBody RequestOrderDto requestOrderDto){
        return orderService.UpdateOrder(requestOrderDto);
    }
    @DeleteMapping
    public void delete(@RequestParam(name ="id") Long id){
        orderService.deleteOrder(id);
    }
}
