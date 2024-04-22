package com.example.market_store.service.Impl;

import com.example.market_store.criteria.OrderDetailsCriteria;
import com.example.market_store.dto.requestDto.RequestOrderDetailsDto;
import com.example.market_store.dto.responseDto.ResponseOrderDetailsDto;
import com.example.market_store.entitie.Order;
import com.example.market_store.entitie.OrderDetails;
import com.example.market_store.entitie.ProductVariant;
import com.example.market_store.entitie.Users;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.OrderDetailsMapper;
import com.example.market_store.repositorie.OrderDetailsRepo;
import com.example.market_store.repositorie.OrderRepo;
import com.example.market_store.repositorie.ProductVariantRepo;
import com.example.market_store.service.OrderDetailsService;
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
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private OrderDetailsRepo orderDetailsRepo;
    private OrderRepo orderRepo;
    private ProductVariantRepo productVariantRepo;
    private OrderDetailsMapper orderDetailsMapper;
    @Override
    public Page<ResponseOrderDetailsDto> findOrderDetailsByCriteria(OrderDetailsCriteria orderDetailsCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<OrderDetails> orderDetailsPage= orderDetailsRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (orderDetailsCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),orderDetailsCriteria.getId()));
            }
            if (orderDetailsCriteria.getOrderId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("orderId"),orderDetailsCriteria.getOrderId()));
            }
            if (orderDetailsCriteria.getProductVariantId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("productVariantId"),orderDetailsCriteria.getProductVariantId()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return orderDetailsMapper.modelToDtos(orderDetailsPage);
    }

    @Override
    public ResponseOrderDetailsDto addOrderDetails(RequestOrderDetailsDto requestOrderDetailsDto) {
        String generatedCodeOrderDetails= "ORDD" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        OrderDetails orderDetailsToSave = orderDetailsMapper.dtoToModel(requestOrderDetailsDto);
        orderDetailsToSave.setOrderDetailsCode(generatedCodeOrderDetails);
        Optional<ProductVariant> productVariant= productVariantRepo.findById(requestOrderDetailsDto.getProductVariantId());
        orderDetailsToSave.setProductVariant(productVariant.get());
        Optional<Order> order= orderRepo.findById(requestOrderDetailsDto.getOrderId());
        orderDetailsToSave.setOrder(order.get());
        Optional<OrderDetails> existingOrderDetails = orderDetailsRepo.findByOrderDetailsCode(orderDetailsToSave.getOrderDetailsCode());
        if (existingOrderDetails.isPresent()) {
            throw new EntityAlreadyExisteException("Order details already exists with id: " + requestOrderDetailsDto.getOrderDetailsCode());
        }
        OrderDetails savedOrderDetails = orderDetailsRepo.save(orderDetailsToSave);
        return orderDetailsMapper.modelToDto(savedOrderDetails);
    }

    @Override
    public ResponseOrderDetailsDto UpdateOrderDetails(RequestOrderDetailsDto requestOrderDetailsDto) {
        Optional<OrderDetails> existingOrderDetails = orderDetailsRepo.findByOrderDetailsCode(requestOrderDetailsDto.getOrderDetailsCode());
        if (existingOrderDetails.isEmpty()){
            throw new EntityNotFoundException("Order details Not Found   ");
        }
        OrderDetails orderDetailsToUpdate = orderDetailsMapper.dtoToModel(requestOrderDetailsDto);
        orderDetailsToUpdate.setOrderDetailsCode(requestOrderDetailsDto.getOrderDetailsCode());
        orderDetailsToUpdate.setProductVariant(productVariantRepo.findById(requestOrderDetailsDto.getProductVariantId()).get());
        orderDetailsToUpdate.setOrder(orderRepo.findById(requestOrderDetailsDto.getOrderId()).get());
        OrderDetails updatedOrderDetails= orderDetailsRepo.save(orderDetailsToUpdate);
        return orderDetailsMapper.modelToDto(updatedOrderDetails);
    }

    @Override
    public void deleteOrderDetails(long id) {
        Optional<OrderDetails> orderDetails = orderDetailsRepo.findById(id);
        if (orderDetails.isEmpty()){
            throw new EntityNotFoundException("Order details Not Found ID :  "+id);
        }
        orderDetailsRepo.deleteById(id);
    }
}
