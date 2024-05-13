package com.example.market_store.Controller;

import com.example.market_store.criteria.ProductCriteria;
import com.example.market_store.criteria.SubCategoryCriteria;
import com.example.market_store.dto.requestDto.RequestProductDto;
import com.example.market_store.dto.requestDto.RequestSubCategoryDto;
import com.example.market_store.dto.responseDto.ResponseProductDto;
import com.example.market_store.dto.responseDto.ResponseSubCategoryDto;
import com.example.market_store.service.ProductService;
import com.example.market_store.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @GetMapping
    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    Page<ResponseProductDto> getProductByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                      @RequestParam(defaultValue = "10" , name = "size") int size,
                                                      @RequestParam( name = "id", required = false) Long id ,
                                                      @RequestParam(name = "name", required = false) String name ,
                                                      @RequestParam(name = "productCode", required = false) String productCode){

        ProductCriteria productCriteria = new ProductCriteria();
        productCriteria.setId(id);
        productCriteria.setName(name);
        productCriteria.setProductCode(productCode);
        return productService.findProductByCriteria(productCriteria,page,size);
    }
    @PostMapping
    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    public ResponseProductDto save(@RequestBody RequestProductDto requestProductDto){
        return productService.addProduct(requestProductDto);
    }
    @PutMapping
    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    public ResponseProductDto update(@RequestBody RequestProductDto requestProductDto){
        return productService.UpdateProduct(requestProductDto);
    }
    @DeleteMapping
    @PreAuthorize("hasRole('admin') or hasRole('seller')")
    public void delete(@RequestParam(name ="id") Long id){
        productService.deleteProduct(id);
    }
}
