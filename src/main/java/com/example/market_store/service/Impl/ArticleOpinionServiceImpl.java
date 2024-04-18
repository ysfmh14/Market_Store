package com.example.market_store.service.Impl;

import com.example.market_store.criteria.ArticleOpinionCriteria;
import com.example.market_store.dto.requestDto.RequestArticleOpinionDto;
import com.example.market_store.dto.responseDto.ResponseArticleOpinionDto;
import com.example.market_store.entitie.*;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.ArticleOpinionMapper;
import com.example.market_store.repositorie.ArticleOpinionRepo;
import com.example.market_store.repositorie.ProductRepo;
import com.example.market_store.repositorie.UsersRepo;
import com.example.market_store.service.ArticleOpinionService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArticleOpinionServiceImpl implements ArticleOpinionService {
    private ArticleOpinionMapper articleOpinionMapper;
    private ArticleOpinionRepo articleOpinionRepo;
    private UsersRepo userRepo;
    private ProductRepo productRepo;
    @Override
    public Page<ResponseArticleOpinionDto> findArticleOpinionByCriteria(ArticleOpinionCriteria articleOpinionCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<ArticleOpinion> articleOpinionPage = articleOpinionRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (articleOpinionCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),articleOpinionCriteria.getId()));
            }
            if (articleOpinionCriteria.getOpinionCode() != null){
                predicateList.add(criteriaBuilder.equal(root.get("opinionCode"),articleOpinionCriteria.getOpinionCode()));
            }
            if (articleOpinionCriteria.getProductId()!= null){
                predicateList.add(criteriaBuilder.equal(root.get("productId"),articleOpinionCriteria.getProductId()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return articleOpinionMapper.modelToDtos(articleOpinionPage);
    }

    @Override
    public ResponseArticleOpinionDto addArticleOpinion(RequestArticleOpinionDto requestArticleOpinionDto) {
        String generatedCodeArticleOpinion = "AO" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        ArticleOpinion articleOpinionToSave = articleOpinionMapper.dtoToModel(requestArticleOpinionDto);
        articleOpinionToSave.setOpinionCode(generatedCodeArticleOpinion);
        Optional<Product> product = productRepo.findById(requestArticleOpinionDto.getProductId());
        articleOpinionToSave.setProduct(product.get());
        Optional<Users> user = userRepo.findById(requestArticleOpinionDto.getUserId());
        articleOpinionToSave.setUser(user.get());
        Optional<ArticleOpinion> existingArticleOpinion = articleOpinionRepo.findByOpinionCode(articleOpinionToSave.getOpinionCode());
        if (existingArticleOpinion.isPresent()) {
            throw new EntityAlreadyExisteException("opinion already exists with id: " + requestArticleOpinionDto.getId());
        }
        ArticleOpinion savedArticleOpinion = articleOpinionRepo.save(articleOpinionToSave);
        return articleOpinionMapper.modelToDto(savedArticleOpinion);
    }

    @Override
    public ResponseArticleOpinionDto UpdateArticleOpinion(RequestArticleOpinionDto requestArticleOpinionDto) {
        Optional<ArticleOpinion> existingArticleOpinion = articleOpinionRepo.findByOpinionCode(requestArticleOpinionDto.getOpinionCode());
        if (existingArticleOpinion.isEmpty()){
            throw new EntityNotFoundException("Opinion Not Found   ");
        }
        ArticleOpinion articleOpinionToUpdate = articleOpinionMapper.dtoToModel(requestArticleOpinionDto);
        articleOpinionToUpdate.setOpinionCode(requestArticleOpinionDto.getOpinionCode());
        articleOpinionToUpdate.setProduct(productRepo.findById(requestArticleOpinionDto.getProductId()).get());
        articleOpinionToUpdate.setUser(userRepo.findById(requestArticleOpinionDto.getUserId()).get());
        ArticleOpinion updatedArticleOpinion= articleOpinionRepo.save(articleOpinionToUpdate);
        return articleOpinionMapper.modelToDto(updatedArticleOpinion);
    }

    @Override
    public void deleteArticleOpinion(long id) {
        Optional<ArticleOpinion> articleOpinion = articleOpinionRepo.findById(id);
        if (articleOpinion.isEmpty()){
            throw new EntityNotFoundException("Opinion Not Found ID :  "+id);
        }
        articleOpinionRepo.deleteById(id);
    }
}
