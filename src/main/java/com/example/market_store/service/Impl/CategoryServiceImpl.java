package com.example.market_store.service.Impl;

import com.example.market_store.criteria.CategoryCriteria;
import com.example.market_store.dto.requestDto.RequestCategoryDto;
import com.example.market_store.dto.responseDto.ResponseCategoryDto;
import com.example.market_store.entitie.Category;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.CategoryMapper;
import com.example.market_store.repositorie.CategoryRepo;
import com.example.market_store.service.CategoryService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
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
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepo categoryRepo;
    private CategoryMapper categoryMapper;
    @Override
    public Page<ResponseCategoryDto> findCategoryByCriteria(CategoryCriteria categoryCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (categoryCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),categoryCriteria.getId()));
            }
            if (categoryCriteria.getName() != null){
                predicateList.add(criteriaBuilder.equal(root.get("name"),categoryCriteria.getName()));
            }
            if (categoryCriteria.getCategoryCode() != null){
                predicateList.add(criteriaBuilder.equal(root.get("categoryCode"),categoryCriteria.getCategoryCode()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return categoryMapper.modelToDtos(categoryPage);
    }

    @Override
    public ResponseCategoryDto addCategory(RequestCategoryDto requestCategoryDto) {
        String generatedCodeCategory = "CAT" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Category categoryToSave = categoryMapper.dtoToModel(requestCategoryDto);
        categoryToSave.setCategoryCode(generatedCodeCategory);
        Optional<Category> existingCategory = categoryRepo.findByCategoryCode(categoryToSave.getCategoryCode());
//        System.out.println(existingSeller.get());
        if (existingCategory.isPresent()) {
            throw new EntityAlreadyExisteException("Category already exists with id: " + requestCategoryDto.getId());
        }
        Category savedCategory = categoryRepo.save(categoryToSave);
        return categoryMapper.modelToDto(savedCategory);
    }

    @Override
    public ResponseCategoryDto UpdateCategory(RequestCategoryDto requestCategoryDto) {
        Optional<Category> existingCategory = categoryRepo.findByCategoryCode(requestCategoryDto.getCategoryCode());
        if (existingCategory.isEmpty()){
            throw new EntityNotFoundException("Category Not Found   ");
        }
        Category categoryToUpdate = categoryMapper.dtoToModel(requestCategoryDto);
        categoryToUpdate.setId(existingCategory.get().getId());
        categoryToUpdate.setCategoryCode(existingCategory.get().getCategoryCode());
        Category updatedCategory= categoryRepo.save(categoryToUpdate);
        return categoryMapper.modelToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(String categoryCode) {
        Optional<Category> category = categoryRepo.findByCategoryCode(categoryCode);
        if (category.isEmpty()){
            throw new EntityNotFoundException("Category Not Found code :  "+categoryCode);
        }
        categoryRepo.deleteByCategoryCode(categoryCode);

    }
}
