package com.example.market_store.service.Impl;

import com.example.market_store.criteria.ProductVariantCriteria;
import com.example.market_store.dto.requestDto.RequestProductVariantDto;
import com.example.market_store.dto.responseDto.ResponseProductVariantDto;
import com.example.market_store.entitie.Product;
import com.example.market_store.entitie.ProductVariant;
import com.example.market_store.entitie.Seller;
import com.example.market_store.entitie.SubCategory;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.ProductVariantMapper;
import com.example.market_store.repositorie.ProductRepo;
import com.example.market_store.repositorie.ProductVariantRepo;
import com.example.market_store.service.ProductVariantService;
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
public class ProductVariantServiceImpl implements ProductVariantService {
    private ProductVariantRepo productVariantRepo;
    private ProductRepo productRepo;
    private ProductVariantMapper productVariantMapper;
    @Override
    public Page<ResponseProductVariantDto> findProductVariantByCriteria(ProductVariantCriteria productVariantCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<ProductVariant> productVariantPage = productVariantRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (productVariantCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),productVariantCriteria.getId()));
            }
            if (productVariantCriteria.getProductId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("productId"),productVariantCriteria.getProductId()));
            }
            if (productVariantCriteria.getProductVariantCode()!= null){
                predicateList.add(criteriaBuilder.equal(root.get("categoryCode"),productVariantCriteria.getProductVariantCode()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return productVariantMapper.modelToDtos(productVariantPage);
    }

    @Override
    public ResponseProductVariantDto addProductVariant(RequestProductVariantDto requestProductVariantDto) {
        String generatedCodeProductVariant = "PRDV" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        ProductVariant productVariantToSave = productVariantMapper.dtoToModel(requestProductVariantDto);
        productVariantToSave.setProductVariantCode(generatedCodeProductVariant);
        Optional<Product> product = productRepo.findByProductCode(requestProductVariantDto.getProductCode());
        productVariantToSave.setProduct(product.get());
        Optional<ProductVariant> existingProductVariant = productVariantRepo.findByProductVariantCode(productVariantToSave.getProductVariantCode());
        if (existingProductVariant.isPresent()) {
            throw new EntityAlreadyExisteException("Product variant already exists with id: " + requestProductVariantDto.getId());
        }
        ProductVariant savedProductVariant = productVariantRepo.save(productVariantToSave);
        return productVariantMapper.modelToDto(savedProductVariant);
    }

    @Override
    public ResponseProductVariantDto UpdateProductVariant(RequestProductVariantDto requestProductVariantDto) {
        Optional<ProductVariant> existingProductVariant = productVariantRepo.findByProductVariantCode(requestProductVariantDto.getProductVariantCode());
        if (existingProductVariant.isEmpty()){
            throw new EntityNotFoundException("Product variant Not Found   ");
        }
        ProductVariant productVariantToUpdate = productVariantMapper.dtoToModel(requestProductVariantDto);
        productVariantToUpdate.setProductVariantCode(requestProductVariantDto.getProductVariantCode());
        productVariantToUpdate.setProduct(productRepo.findByProductCode(requestProductVariantDto.getProductCode()).get());
        ProductVariant updatedProductVariant= productVariantRepo.save(productVariantToUpdate);
        return productVariantMapper.modelToDto(updatedProductVariant);
    }

    @Override
    public void deleteProductVariant(long id) {
        Optional<ProductVariant> productVariant = productVariantRepo.findById(id);
        if (productVariant.isEmpty()){
            throw new EntityNotFoundException("Product variant Not Found ID :  "+id);
        }
        productVariantRepo.deleteById(id);
    }
}
