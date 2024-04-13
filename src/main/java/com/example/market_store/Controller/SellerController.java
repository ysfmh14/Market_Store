package com.example.market_store.Controller;

import com.example.market_store.criteria.SellerCriteria;
import com.example.market_store.dto.requestDto.RequestSellerDto;
import com.example.market_store.dto.responseDto.ResponseSellerDto;
import com.example.market_store.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private SellerService sellerService;
    @GetMapping
    Page<ResponseSellerDto> getSellerByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                               @RequestParam(defaultValue = "10" , name = "size") int size,
                                               @RequestParam( name = "id", required = false) Long id ,
                                               @RequestParam(name = "lastName", required = false) String lastName ,
                                               @RequestParam(name = "firstName", required = false) String firstName){

        SellerCriteria sellerCriteria = new SellerCriteria();
        sellerCriteria.setId(id);
        sellerCriteria.setLastName(lastName);
        sellerCriteria.setFirstName(firstName);
        return sellerService.findSellerByCriteria(sellerCriteria,page,size);
    }
    @PostMapping
    public ResponseSellerDto save(@RequestBody RequestSellerDto requestSellerDto){
        return sellerService.addSeller(requestSellerDto);
    }
    @PutMapping
    public ResponseSellerDto update(@RequestBody RequestSellerDto requestSellerDto){
        return sellerService.UpdateSeller(requestSellerDto);
    }
    @DeleteMapping
    public void delete(@RequestParam(name ="id") Long id){
        sellerService.deleteSeller(id);
    }

}
