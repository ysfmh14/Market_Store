package com.example.market_store.Controller;

import com.example.market_store.criteria.ArticleOpinionCriteria;
import com.example.market_store.criteria.SubCategoryCriteria;
import com.example.market_store.dto.requestDto.RequestArticleOpinionDto;
import com.example.market_store.dto.requestDto.RequestSubCategoryDto;
import com.example.market_store.dto.responseDto.ResponseArticleOpinionDto;
import com.example.market_store.dto.responseDto.ResponseSubCategoryDto;
import com.example.market_store.service.ArticleOpinionService;
import com.example.market_store.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/articleopinions")
public class ArticleOpinionController {
    private ArticleOpinionService articleOpinionService;
    @GetMapping
    Page<ResponseArticleOpinionDto> getArticleOpinionByCriteria(@RequestParam(defaultValue = "0", name ="page") int page,
                                                             @RequestParam(defaultValue = "10" , name = "size") int size,
                                                             @RequestParam( name = "id", required = false) Long id ,
                                                             @RequestParam(name = "opinionCode", required = false) String opinionCode ,
                                                             @RequestParam(name = "productId", required = false) Long productId){
        ArticleOpinionCriteria articleOpinionCriteria = new ArticleOpinionCriteria();
        articleOpinionCriteria.setId(id);
        articleOpinionCriteria.setOpinionCode(opinionCode);
        articleOpinionCriteria.setProductId(productId);
        return articleOpinionService.findArticleOpinionByCriteria(articleOpinionCriteria,page,size);
    }
    @PostMapping
    @PreAuthorize("hasRole('user')")
    public ResponseArticleOpinionDto save(@RequestBody RequestArticleOpinionDto requestArticleOpinionDto){
        return articleOpinionService.addArticleOpinion(requestArticleOpinionDto);
    }
    @PutMapping
    @PreAuthorize("hasRole('user')")
    public ResponseArticleOpinionDto update(@RequestBody RequestArticleOpinionDto requestArticleOpinionDto){
        return articleOpinionService.UpdateArticleOpinion(requestArticleOpinionDto);
    }
    @DeleteMapping
    @PreAuthorize("hasRole('user')")
    public void delete(@RequestParam(name ="id") Long id){
        articleOpinionService.deleteArticleOpinion(id);
    }
}
