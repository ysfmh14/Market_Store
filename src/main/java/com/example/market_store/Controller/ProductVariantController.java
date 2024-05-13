package com.example.market_store.Controller;

import com.example.market_store.criteria.ProductCriteria;
import com.example.market_store.criteria.ProductVariantCriteria;
import com.example.market_store.dto.requestDto.RequestProductDto;
import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import com.example.market_store.dto.responseDto.ResponseProductDto;
import com.example.market_store.dto.responseDto.ResponseProductVariantDto;
import com.example.market_store.service.ProductService;
import com.example.market_store.service.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/productvariants")
public class ProductVariantController {
    private ProductVariantService productVariantService;
    @GetMapping
    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    Page<ResponseProductVariantDto> getProductVariantByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                         @RequestParam(defaultValue = "10" , name = "size") int size,
                                                         @RequestParam( name = "id", required = false) Long id ,
                                                         @RequestParam(name = "productId", required = false) Long productId ,
                                                         @RequestParam(name = "productVariantCode", required = false) String productVariantCode){

        ProductVariantCriteria productVariantCriteria = new ProductVariantCriteria();
        productVariantCriteria.setId(id);
        productVariantCriteria.setProductId(productId);
        productVariantCriteria.setProductVariantCode(productVariantCode);
        return productVariantService.findProductVariantByCriteria(productVariantCriteria,page,size);
    }
    @PostMapping
    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    public ResponseProductVariantDto save(@RequestBody RequestProductVariantDto requestProductVariantDto){
        return productVariantService.addProductVariant(requestProductVariantDto);
    }
    @PutMapping
    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    public ResponseProductVariantDto update(@RequestBody RequestProductVariantDto requestProductVariantDto){
        return productVariantService.UpdateProductVariant(requestProductVariantDto);
    }
    @DeleteMapping
    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    public void delete(@RequestParam(name ="id") Long id){
        productVariantService.deleteProductVariant(id);
    }
}
