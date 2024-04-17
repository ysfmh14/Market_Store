package com.example.market_store.Controller;

import com.example.market_store.criteria.DeliveryCriteria;
import com.example.market_store.criteria.DeliverymanCriteria;
import com.example.market_store.dto.requestDto.RequestDeliveryDto;
import com.example.market_store.dto.requestDto.RequestDeliverymanDto;
import com.example.market_store.dto.responseDto.ResponseDeliveryDto;
import com.example.market_store.dto.responseDto.ResponseDeliverymanDto;
import com.example.market_store.service.DeliveryService;
import com.example.market_store.service.DeliverymanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/deliveries")
public class DeliveryController {
    private DeliveryService deliveryService;
    @GetMapping
    Page<ResponseDeliveryDto> getSubDeliveryByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                          @RequestParam(defaultValue = "10" , name = "size") int size,
                                                          @RequestParam( name = "id", required = false) Long id ,
                                                          @RequestParam(name = "deliveryCode", required = false) String deliveryCode ,
                                                          @RequestParam(name = "deliveryStatus", required = false) String deliveryStatus){

        DeliveryCriteria deliveryCriteria = new DeliveryCriteria();
        deliveryCriteria.setId(id);
        deliveryCriteria.setDeliveryCode(deliveryCode);
        deliveryCriteria.setDeliveryStatus(deliveryStatus);
        return deliveryService.findDeliveryByCriteria(deliveryCriteria,page,size);
    }
    @PostMapping
    public ResponseDeliveryDto save(@RequestBody RequestDeliveryDto requestDeliveryDto){
        return deliveryService.addDelivery(requestDeliveryDto);
    }
    @PutMapping
    public ResponseDeliveryDto update(@RequestBody RequestDeliveryDto requestDeliveryDto){
        return deliveryService.UpdateDelivery(requestDeliveryDto);
    }
    @DeleteMapping
    public void delete(@RequestParam(name ="id") Long id){
        deliveryService.deleteDelivery(id);
    }
}
