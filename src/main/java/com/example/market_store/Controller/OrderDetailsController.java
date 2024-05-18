package com.example.market_store.Controller;

import com.example.market_store.criteria.OrderCriteria;
import com.example.market_store.criteria.OrderDetailsCriteria;
import com.example.market_store.dto.requestDto.RequestOrderDetailsDto;
import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.responseDto.ResponseOrderDetailsDto;
import com.example.market_store.dto.responseDto.ResponseOrderDto;
import com.example.market_store.service.OrderDetailsService;
import com.example.market_store.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/orderdetails")
public class OrderDetailsController {
    private OrderDetailsService orderDetailsService;
    @GetMapping
//    @PreAuthorize("hasRole('admin') or hasRole('user')")
    Page<ResponseOrderDetailsDto> getOrderDetailsByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                     @RequestParam(defaultValue = "10" , name = "size") int size,
                                                     @RequestParam( name = "id", required = false) Long id ,
                                                     @RequestParam(name = "orderId", required = false) Long orderId ,
                                                     @RequestParam(name = "productVariantId", required = false) Long productVariantId ){

        OrderDetailsCriteria orderDetailsCriteria = new OrderDetailsCriteria();
        orderDetailsCriteria.setId(id);
        orderDetailsCriteria.setOrderId(orderId);
        orderDetailsCriteria.setProductVariantId(productVariantId);
        return orderDetailsService.findOrderDetailsByCriteria(orderDetailsCriteria,page,size);
    }
    @PostMapping
//    @PreAuthorize("hasRole('admin') or hasRole('user')")
    public ResponseOrderDetailsDto save(@RequestBody RequestOrderDetailsDto requestOrderDetailsDto){
        return orderDetailsService.addOrderDetails(requestOrderDetailsDto);
    }
    @PutMapping
//    @PreAuthorize("hasRole('admin') or hasRole('user')")
    public ResponseOrderDetailsDto update(@RequestBody RequestOrderDetailsDto requestOrderDetailsDto){
        return orderDetailsService.UpdateOrderDetails(requestOrderDetailsDto);
    }
    @DeleteMapping
//    @PreAuthorize("hasRole('admin') or hasRole('user')")
    public void delete(@RequestParam(name ="id") Long id){
        orderDetailsService.deleteOrderDetails(id);
    }
}
