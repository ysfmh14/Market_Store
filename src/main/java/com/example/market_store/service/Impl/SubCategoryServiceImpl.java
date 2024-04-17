package com.example.market_store.service.Impl;

import com.example.market_store.criteria.SubCategoryCriteria;
import com.example.market_store.dto.requestDto.RequestSubCategoryDto;
import com.example.market_store.dto.responseDto.ResponseSubCategoryDto;
import com.example.market_store.entitie.Category;
import com.example.market_store.entitie.SubCategory;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.SubCategoryMapper;
import com.example.market_store.repositorie.CategoryRepo;
import com.example.market_store.repositorie.SubCategoryRepo;
import com.example.market_store.service.SubCategoryService;
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
public class SubCategoryServiceImpl implements SubCategoryService {
    private SubCategoryMapper subCategoryMapper;
    private SubCategoryRepo subCategoryRepo;
    private CategoryRepo categoryRepo;
    @Override
    public Page<ResponseSubCategoryDto> findSubCategoryByCriteria(SubCategoryCriteria subCategoryCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<SubCategory> subCategoryPage = subCategoryRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (subCategoryCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),subCategoryCriteria.getId()));
            }
            if (subCategoryCriteria.getName() != null){
                predicateList.add(criteriaBuilder.equal(root.get("name"),subCategoryCriteria.getName()));
            }
            if (subCategoryCriteria.getSubCategoryCode() != null){
                predicateList.add(criteriaBuilder.equal(root.get("categoryCode"),subCategoryCriteria.getSubCategoryCode()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return subCategoryMapper.modelToDtos(subCategoryPage);
    }

    @Override
    public ResponseSubCategoryDto addSubCategory(RequestSubCategoryDto requestSubCategoryDto) {
        String generatedCodeSubCategory = "SCAT" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        SubCategory subCategoryToSave = subCategoryMapper.dtoToModel(requestSubCategoryDto);
        subCategoryToSave.setSubCategoryCode(generatedCodeSubCategory);
        Optional<Category> category= categoryRepo.findById(requestSubCategoryDto.getCategoryId());
        subCategoryToSave.setCategory(category.get());
        Optional<SubCategory> existingSubCategory = subCategoryRepo.findBySubCategoryCode(subCategoryToSave.getSubCategoryCode());
        if (existingSubCategory.isPresent()) {
            throw new EntityAlreadyExisteException("SubCategory already exists with id: " + requestSubCategoryDto.getId());
        }
        SubCategory savedSubCategory = subCategoryRepo.save(subCategoryToSave);
        return subCategoryMapper.modelToDto(savedSubCategory);
    }


    @Override
    public ResponseSubCategoryDto UpdateSubCategory(RequestSubCategoryDto requestSubCategoryDto) {

        Optional<SubCategory> existingSubCategory = subCategoryRepo.findBySubCategoryCode(requestSubCategoryDto.getSubCategoryCode());
        if (existingSubCategory.isEmpty()){
            throw new EntityNotFoundException("SubCategory Not Found   ");
        }
        SubCategory subCategoryToUpdate = subCategoryMapper.dtoToModel(requestSubCategoryDto);
        subCategoryToUpdate.setSubCategoryCode(requestSubCategoryDto.getSubCategoryCode());
        subCategoryToUpdate.setCategory(categoryRepo.findById(requestSubCategoryDto.getCategoryId()).get());
        SubCategory updatedSubCategory= subCategoryRepo.save(subCategoryToUpdate);
        return subCategoryMapper.modelToDto(updatedSubCategory);

    }

    @Override
    public void deleteSubCategory(long id) {
        Optional<SubCategory> subcategory = subCategoryRepo.findById(id);
        if (subcategory.isEmpty()){
            throw new EntityNotFoundException("SubCategory Not Found ID :  "+id);
        }
        subCategoryRepo.deleteById(id);

    }
}
