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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/deliverymen")
public class DeliverymanController {
    private DeliverymanService deliverymanService;
    @GetMapping
    @PreAuthorize("hasRole('client_admin') or hasRole('client_deliverymen')")
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
    @PreAuthorize("hasRole('client_admin') or hasRole('client_deliverymen')")
    public ResponseDeliverymanDto save(@RequestBody RequestDeliverymanDto requestDeliverymanDto){
        return deliverymanService.addDeliveryman(requestDeliverymanDto);
    }
    @PutMapping
    @PreAuthorize("hasRole('client_admin') or hasRole('client_deliverymen')")
    public ResponseDeliverymanDto update(@RequestBody RequestDeliverymanDto requestDeliverymanDto){
        return deliverymanService.UpdateDeliveryman(requestDeliverymanDto);
    }
    @DeleteMapping
    @PreAuthorize("hasRole('client_admin') or hasRole('client_deliverymen')")
    public void delete(@RequestParam(name ="id") Long id){
        deliverymanService.deleteDeliveryman(id);
    }
}
