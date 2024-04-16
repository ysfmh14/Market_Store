package com.example.market_store.Controller;

import com.example.market_store.criteria.CategoryCriteria;
import com.example.market_store.criteria.SubCategoryCriteria;
import com.example.market_store.dto.requestDto.RequestCategoryDto;
import com.example.market_store.dto.requestDto.RequestSubCategoryDto;
import com.example.market_store.dto.responseDto.ResponseCategoryDto;
import com.example.market_store.dto.responseDto.ResponseSubCategoryDto;
import com.example.market_store.service.CategoryService;
import com.example.market_store.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/subcategories")
public class SubCategoryController {
    private SubCategoryService subCategoryService;
    @GetMapping
    Page<ResponseSubCategoryDto> getSubCategoryByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                          @RequestParam(defaultValue = "10" , name = "size") int size,
                                                          @RequestParam( name = "id", required = false) Long id ,
                                                          @RequestParam(name = "name", required = false) String name ,
                                                          @RequestParam(name = "categoryCode", required = false) String subCategoryCode){

        SubCategoryCriteria subCategoryCriteria = new SubCategoryCriteria();
        subCategoryCriteria.setId(id);
        subCategoryCriteria.setName(name);
        subCategoryCriteria.setSubCategoryCode(subCategoryCode);
        return subCategoryService.findSubCategoryByCriteria(subCategoryCriteria,page,size);
    }
    @PostMapping
    public ResponseSubCategoryDto save(@RequestBody RequestSubCategoryDto requestSubCategoryDto){
        return subCategoryService.addSubCategory(requestSubCategoryDto);
    }
    @PutMapping
    public ResponseSubCategoryDto update(@RequestBody RequestSubCategoryDto requestSubCategoryDto){
        return subCategoryService.UpdateSubCategory(requestSubCategoryDto);
    }
    @DeleteMapping
    public void delete(@RequestParam(name ="id") Long id){
        subCategoryService.deleteSubCategory(id);
    }
}
