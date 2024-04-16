package com.example.market_store.Controller;

import com.example.market_store.criteria.DeliverymanCriteria;
import com.example.market_store.criteria.SubCategoryCriteria;
import com.example.market_store.dto.requestDto.RequestDeliverymanDto;
import com.example.market_store.dto.requestDto.RequestSubCategoryDto;
import com.example.market_store.dto.responseDto.ResponseDeliverymanDto;
import com.example.market_store.dto.responseDto.ResponseSubCategoryDto;
import com.example.market_store.service.DeliverymanService;
import com.example.market_store.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/deliverymen")
public class DeliverymanController {
    private DeliverymanService deliverymanService;
    @GetMapping
    Page<ResponseDeliverymanDto> getSubDeliverymanByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                          @RequestParam(defaultValue = "10" , name = "size") int size,
                                                          @RequestParam( name = "id", required = false) Long id ,
                                                             @RequestParam(name = "lastName", required = false) String lastName ,
                                                             @RequestParam(name = "firstName", required = false) String firstName){

        DeliverymanCriteria deliverymanCriteria = new DeliverymanCriteria();
        deliverymanCriteria.setId(id);
        deliverymanCriteria.setLastName(lastName);
        deliverymanCriteria.setFirstName(firstName);
        return deliverymanService.findDeliverymanByCriteria(deliverymanCriteria,page,size);
    }
    @PostMapping
    public ResponseDeliverymanDto save(@RequestBody RequestDeliverymanDto requestDeliverymanDto){
        return deliverymanService.addDeliveryman(requestDeliverymanDto);
    }
    @PutMapping
    public ResponseDeliverymanDto update(@RequestBody RequestDeliverymanDto requestDeliverymanDto){
        return deliverymanService.UpdateDeliveryman(requestDeliverymanDto);
    }
    @DeleteMapping
    public void delete(@RequestParam(name ="id") Long id){
        deliverymanService.deleteDeliveryman(id);
    }
}
