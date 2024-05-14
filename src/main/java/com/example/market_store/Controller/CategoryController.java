package com.example.market_store.Controller;

import com.example.market_store.criteria.CategoryCriteria;
import com.example.market_store.dto.requestDto.RequestCategoryDto;
import com.example.market_store.dto.responseDto.ResponseCategoryDto;
import com.example.market_store.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
    private CategoryService categoryService;
    @GetMapping
//    @PreAuthorize("hasRole('admin')")
    Page<ResponseCategoryDto> getCategoryByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                  @RequestParam(defaultValue = "10" , name = "size") int size,
                                                  @RequestParam( name = "id", required = false) Long id ,
                                                  @RequestParam(name = "name", required = false) String name ,
                                                  @RequestParam(name = "categoryCode", required = false) String categoryCode){

        CategoryCriteria categoryCriteria = new CategoryCriteria();
        categoryCriteria.setId(id);
        categoryCriteria.setName(name);
        categoryCriteria.setCategoryCode(categoryCode);
        return categoryService.findCategoryByCriteria(categoryCriteria,page,size);
    }
    @PostMapping
//    @PreAuthorize("hasRole('admin')")
    public ResponseCategoryDto save(@RequestBody RequestCategoryDto requestCategoryDto){
        return categoryService.addCategory(requestCategoryDto);
    }
    @PutMapping
//    @PreAuthorize("hasRole('admin')")
    public ResponseCategoryDto update(@RequestBody RequestCategoryDto requestCategoryDto){
        return categoryService.UpdateCategory(requestCategoryDto);
    }
    @DeleteMapping
//    @PreAuthorize("hasRole('admin')")
    public void delete(@RequestParam(name ="code") String code){
        categoryService.deleteCategory(code);
    }
}
