package com.example.market_store.service.Impl;

import com.example.market_store.criteria.DeliveryCriteria;
import com.example.market_store.dto.requestDto.RequestDeliveryDto;
import com.example.market_store.dto.responseDto.ResponseDeliveryDto;
import com.example.market_store.entitie.Category;
import com.example.market_store.entitie.Delivery;
import com.example.market_store.entitie.Deliveryman;
import com.example.market_store.entitie.SubCategory;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.DeliveryMapper;
import com.example.market_store.repositorie.DeliveryRepo;
import com.example.market_store.repositorie.DeliverymanRepo;
import com.example.market_store.service.DeliveryService;
import com.example.market_store.service.DeliverymanService;
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
public class DeliveryServiceImpl implements DeliveryService {
    private DeliveryMapper deliveryMapper;
    private DeliveryRepo deliveryRepo;
    private DeliverymanRepo deliverymanRepo;
    @Override
    public Page<ResponseDeliveryDto> findDeliveryByCriteria(DeliveryCriteria deliveryCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Delivery> deliveryPage = deliveryRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (deliveryCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),deliveryCriteria.getId()));
            }
            if (deliveryCriteria.getDeliveryCode() != null){
                predicateList.add(criteriaBuilder.equal(root.get("deliveryCode"),deliveryCriteria.getDeliveryCode()));
            }
            if (deliveryCriteria.getDeliveryStatus() != null){
                predicateList.add(criteriaBuilder.equal(root.get("deliveryStatus"), deliveryCriteria.getDeliveryStatus()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return deliveryMapper.modelToDtos(deliveryPage);
    }

    @Override
    public ResponseDeliveryDto addDelivery(RequestDeliveryDto requestDeliveryDto) {
        String generatedCodeDelivery = "DLV" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Delivery deliveryToSave = deliveryMapper.dtoToModel(requestDeliveryDto);
        deliveryToSave.setDeliveryCode(generatedCodeDelivery);
        Optional<Deliveryman> deliveryman= deliverymanRepo.findById(requestDeliveryDto.getDeliverymanId());
        deliveryToSave.setDeliveryman(deliveryman.get());
        Optional<Delivery> existingDelivery = deliveryRepo.findByDeliveryCode(deliveryToSave.getDeliveryCode());
        if (existingDelivery.isPresent()) {
            throw new EntityAlreadyExisteException("SubCategory already exists with id: " + requestDeliveryDto.getId());
        }
        Delivery savedDelivery = deliveryRepo.save(deliveryToSave);
        return deliveryMapper.modelToDto(savedDelivery);
    }

    @Override
    public ResponseDeliveryDto UpdateDelivery(RequestDeliveryDto requestDeliveryDto) {
        Optional<Delivery> existingDelivery = deliveryRepo.findByDeliveryCode(requestDeliveryDto.getDeliveryCode());
        if (existingDelivery.isEmpty()){
            throw new EntityNotFoundException("Delivery Not Found   ");
        }
        Delivery deliveryToUpdate = deliveryMapper.dtoToModel(requestDeliveryDto);
        deliveryToUpdate.setDeliveryCode(requestDeliveryDto.getDeliveryCode());
        deliveryToUpdate.setDeliveryman(deliverymanRepo.findById(requestDeliveryDto.getDeliverymanId()).get());
        Delivery updatedDelivery= deliveryRepo.save(deliveryToUpdate);
        return deliveryMapper.modelToDto(updatedDelivery);
    }

    @Override
    public void deleteDelivery(long id) {
        Optional<Delivery> delivery = deliveryRepo.findById(id);
        if (delivery.isEmpty()){
            throw new EntityNotFoundException("Delivery Not Found ID :  "+id);
        }
        deliveryRepo.deleteById(id);
    }
}
