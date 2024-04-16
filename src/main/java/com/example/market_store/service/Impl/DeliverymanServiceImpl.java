package com.example.market_store.service.Impl;

import com.example.market_store.criteria.DeliverymanCriteria;
import com.example.market_store.dto.requestDto.RequestDeliverymanDto;
import com.example.market_store.dto.responseDto.ResponseDeliverymanDto;
import com.example.market_store.entitie.Deliveryman;
import com.example.market_store.entitie.Seller;
import com.example.market_store.entitie.SubCategory;
import com.example.market_store.entitie.Users;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.DeliverymanMapper;
import com.example.market_store.repositorie.DeliverymanRepo;
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
public class DeliverymanServiceImpl implements DeliverymanService {
    private DeliverymanMapper deliverymanMapper;
    private DeliverymanRepo deliverymanRepo;
    @Override
    public Page<ResponseDeliverymanDto> findDeliverymanByCriteria(DeliverymanCriteria deliverymanCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Deliveryman> deliverymanPage = deliverymanRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (deliverymanCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),deliverymanCriteria.getId()));
            }
            if (deliverymanCriteria.getFirstName() != null){
                predicateList.add(criteriaBuilder.equal(root.get("firstName"),deliverymanCriteria.getFirstName()));
            }
            if (deliverymanCriteria.getLastName() != null){
                predicateList.add(criteriaBuilder.equal(root.get("lastName"), deliverymanCriteria.getLastName()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return deliverymanMapper.modelToDtos(deliverymanPage);
    }

    @Override
    public ResponseDeliverymanDto addDeliveryman(RequestDeliverymanDto requestDeliverymanDto) {
        String generatedCodeDeliveryman = "SLR" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Deliveryman deliverymanToSave = deliverymanMapper.dtoToModel(requestDeliverymanDto);
        deliverymanToSave.setDeliverymanCode(generatedCodeDeliveryman);
        Optional<Deliveryman> existingDeliveryman = deliverymanRepo.findByDeliverymanCode(deliverymanToSave.getDeliverymanCode());

        if (existingDeliveryman.isPresent()) {
            throw new EntityAlreadyExisteException("Deliveryman already exists with id: " + requestDeliverymanDto.getDeliverymanCode());
        }
        Deliveryman savedDeliveryman = deliverymanRepo.save(deliverymanToSave);
        return deliverymanMapper.modelToDto(savedDeliveryman);
    }

    @Override
    public ResponseDeliverymanDto UpdateDeliveryman(RequestDeliverymanDto requestDeliverymanDto) {
        Optional<Deliveryman> existingDeliveryman = deliverymanRepo.findByDeliverymanCode(requestDeliverymanDto.getDeliverymanCode());
        if (existingDeliveryman.isEmpty()){
            throw new EntityNotFoundException("Deliveryman Not Found ");
        }
        Deliveryman deliverymanToUpdate = deliverymanMapper.dtoToModel(requestDeliverymanDto);
        deliverymanToUpdate.setDeliverymanCode(existingDeliveryman.get().getDeliverymanCode());
        Deliveryman updatedDeliveryman = deliverymanRepo.save(deliverymanToUpdate);
        return deliverymanMapper.modelToDto(updatedDeliveryman);
    }

    @Override
    public void deleteDeliveryman(long id) {
        Optional<Deliveryman> user = deliverymanRepo.findById(id);
        if (!user.isPresent()){
            throw new EntityNotFoundException("Deliveryman Not Found ID :  "+id);
        }
        deliverymanRepo.deleteById(id);

    }
}
