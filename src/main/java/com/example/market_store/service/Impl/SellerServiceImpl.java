package com.example.market_store.service.Impl;

import com.example.market_store.criteria.SellerCriteria;
import com.example.market_store.dto.requestDto.RequestSellerDto;
import com.example.market_store.dto.responseDto.ResponseSellerDto;
import com.example.market_store.entitie.Seller;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.SellerMapper;
import com.example.market_store.repositorie.SellerRepo;
import com.example.market_store.service.KeycloakService;
import com.example.market_store.service.SellerService;
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
public class SellerServiceImpl implements SellerService {
    private SellerRepo sellerRepo;
    private SellerMapper sellerMapper;
    private KeycloakService keycloakService;
    @Override
    public Page<ResponseSellerDto> findSellerByCriteria(SellerCriteria sellerCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Seller> SellerPage = sellerRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (sellerCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),sellerCriteria.getId()));
            }
            if (sellerCriteria.getFirstName() != null){
                predicateList.add(criteriaBuilder.equal(root.get("firstName"),sellerCriteria.getFirstName()));
            }
            if (sellerCriteria.getLastName() != null){
                predicateList.add(criteriaBuilder.equal(root.get("lastName"),sellerCriteria.getLastName()));
            }
            if (sellerCriteria.getSellerCode() != null){
                predicateList.add(criteriaBuilder.equal(root.get("sellerCode"),sellerCriteria.getSellerCode()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return sellerMapper.modelToDtos(SellerPage);
    }

    @Override
    public ResponseSellerDto addSeller(RequestSellerDto requestSellerDto) {

        String generatedCodeSeller = "SLR" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Seller sellerToSave = sellerMapper.dtoToModel(requestSellerDto);
        sellerToSave.setSellerCode(generatedCodeSeller);
        Optional<Seller> existingSeller = sellerRepo.findBySellerCode(sellerToSave.getSellerCode());
//        System.out.println(existingSeller.get());
        if (existingSeller.isPresent()) {
            throw new EntityAlreadyExisteException("Seller already exists with id: " + requestSellerDto.getSellerCode());
        }
        keycloakService.createSeller(requestSellerDto);
        Seller savedSeller = sellerRepo.save(sellerToSave);
        return sellerMapper.modelToDto(savedSeller);
    }

    @Override
    public ResponseSellerDto UpdateSeller(RequestSellerDto requestSellerDto) {
        Optional<Seller> existingSeller = sellerRepo.findBySellerCode(requestSellerDto.getSellerCode());
        if (existingSeller.isEmpty()){
            throw new EntityNotFoundException("Seller Not Found   ");
        }
        Seller sellerToUpdate = sellerMapper.dtoToModel(requestSellerDto);
        sellerToUpdate.setSellerCode(existingSeller.get().getSellerCode());
        Seller updatedSeller= sellerRepo.save(sellerToUpdate);
        keycloakService.updateSeller(requestSellerDto);
        return sellerMapper.modelToDto(updatedSeller);
    }

    @Override
    public void deleteSeller(String sellerCode) {
        Optional<Seller> seller = sellerRepo.findBySellerCode(sellerCode);
        if (seller.isEmpty()){
            throw new EntityNotFoundException("Seller Not Found code :  "+sellerCode);
        }

        sellerRepo.deleteBySellerCode(sellerCode);
        keycloakService.deleteUser(seller.get().getEmail());
    }
}
