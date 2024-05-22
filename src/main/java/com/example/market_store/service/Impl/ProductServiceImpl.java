package com.example.market_store.service.Impl;

import com.example.market_store.criteria.ProductCriteria;
import com.example.market_store.dto.requestDto.RequestProductDto;
import com.example.market_store.dto.responseDto.ResponseProductDto;
import com.example.market_store.entitie.Category;
import com.example.market_store.entitie.Product;
import com.example.market_store.entitie.Seller;
import com.example.market_store.entitie.SubCategory;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.ProductMapper;
import com.example.market_store.repositorie.ProductRepo;
import com.example.market_store.repositorie.SellerRepo;
import com.example.market_store.repositorie.SubCategoryRepo;
import com.example.market_store.service.ProductService;
import jakarta.persistence.criteria.Join;
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
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    private SellerRepo sellerRepo;
    private SubCategoryRepo subCategoryRepo;
    private ProductMapper productMapper;
    @Override
    public Page<ResponseProductDto> findProductByCriteria(ProductCriteria productCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (productCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),productCriteria.getId()));
            }
            if (productCriteria.getName() != null){
                predicateList.add(criteriaBuilder.equal(root.get("name"),productCriteria.getName()));
            }
            if (productCriteria.getProductCode()!= null){
                predicateList.add(criteriaBuilder.equal(root.get("categoryCode"),productCriteria.getProductCode()));
            }
            if (productCriteria.getSellerCode() != null){
                Join<Product, Seller> sellerJoin = root.join("seller");
                predicateList.add(criteriaBuilder.equal(sellerJoin.get("sellerCode"), productCriteria.getSellerCode()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return productMapper.modelToDtos(productPage);
    }

    @Override
    public ResponseProductDto addProduct(RequestProductDto requestProductDto) {
        String generatedCodeProduct = "PRD" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Product productToSave = productMapper.dtoToModel(requestProductDto);
        productToSave.setProductCode(generatedCodeProduct);
        Optional<Seller>  seller = sellerRepo.findById(requestProductDto.getSellerId());
        Optional<SubCategory>  subCategory = subCategoryRepo.findById(requestProductDto.getSubCategoryId());
        productToSave.setSeller(seller.get());
        productToSave.setSubCategory(subCategory.get());
        Optional<Product> existingProduct = productRepo.findByProductCode(productToSave.getProductCode());
        if (existingProduct.isPresent()) {
            throw new EntityAlreadyExisteException("Product already exists with id: " + requestProductDto.getId());
        }
        Product savedProduct = productRepo.save(productToSave);
        return productMapper.modelToDto(savedProduct);
    }

    @Override
    public ResponseProductDto UpdateProduct(RequestProductDto requestProductDto) {
        Optional<Product> existingProduct = productRepo.findByProductCode(requestProductDto.getProductCode());
        if (existingProduct.isEmpty()){
            throw new EntityNotFoundException("Product Not Found   ");
        }
        Product productToUpdate = productMapper.dtoToModel(requestProductDto);
        productToUpdate.setProductCode(requestProductDto.getProductCode());
        productToUpdate.setSeller(sellerRepo.findById(requestProductDto.getSellerId()).get());
        productToUpdate.setSubCategory(subCategoryRepo.findById(requestProductDto.getSubCategoryId()).get());
        Product updatedProduct= productRepo.save(productToUpdate);
        return productMapper.modelToDto(updatedProduct);
    }


    @Override
    public void deleteProduct(long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()){
            throw new EntityNotFoundException("Product Not Found ID :  "+id);
        }
        productRepo.deleteById(id);
    }
}
