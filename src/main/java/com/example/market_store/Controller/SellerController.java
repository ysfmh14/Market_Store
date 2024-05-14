package com.example.market_store.Controller;

import com.example.market_store.criteria.SellerCriteria;
import com.example.market_store.dto.requestDto.RequestSellerDto;
import com.example.market_store.dto.responseDto.ResponseSellerDto;
import com.example.market_store.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/sellers")
@CrossOrigin("*")
public class SellerController {
    private SellerService sellerService;
    @GetMapping
//    @PreAuthorize("hasRole('admin')")
    Page<ResponseSellerDto> getSellerByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                               @RequestParam(defaultValue = "10" , name = "size") int size,
                                               @RequestParam( name = "id", required = false) Long id ,
                                               @RequestParam(name = "lastName", required = false) String lastName ,
                                               @RequestParam(name = "firstName", required = false) String firstName,
                                                @RequestParam(name = "sellerCode", required = false) String sellerCode){

        SellerCriteria sellerCriteria = new SellerCriteria();
        sellerCriteria.setId(id);
        sellerCriteria.setLastName(lastName);
        sellerCriteria.setFirstName(firstName);
        sellerCriteria.setSellerCode(sellerCode);
        return sellerService.findSellerByCriteria(sellerCriteria,page,size);
    }


    @PostMapping
//    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    public ResponseSellerDto save(@RequestBody RequestSellerDto requestSellerDto){
        return sellerService.addSeller(requestSellerDto);
    }
    @PutMapping
//    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    public ResponseSellerDto update(@RequestBody RequestSellerDto requestSellerDto){
        return sellerService.UpdateSeller(requestSellerDto);
    }

    @DeleteMapping
//    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    public void delete(@RequestParam(name ="code") String code){
        sellerService.deleteSeller(code);
    }

}
