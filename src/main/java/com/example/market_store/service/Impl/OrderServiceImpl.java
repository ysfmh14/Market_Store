package com.example.market_store.service.Impl;

import com.example.market_store.criteria.OrderCriteria;
import com.example.market_store.dto.requestDto.RequestOrderDetailsDto;
import com.example.market_store.dto.requestDto.RequestOrderDto;
import com.example.market_store.dto.responseDto.ResponseOrderDetailsDto;
import com.example.market_store.dto.responseDto.ResponseOrderDto;
import com.example.market_store.entitie.*;
import com.example.market_store.exception.EntityAlreadyExisteException;
import com.example.market_store.exception.EntityNotFoundException;
import com.example.market_store.mapper.OrderMapper;
import com.example.market_store.repositorie.OrderRepo;
import com.example.market_store.repositorie.UsersRepo;
import com.example.market_store.service.OrderDetailsService;
import com.example.market_store.service.OrderService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepo orderRepo;
    private UsersRepo userRepo;
    private OrderMapper orderMapper;
    private OrderDetailsService orderDetailsService;
    @Override
    public Page<ResponseOrderDto> findOrderByCriteria(OrderCriteria orderCriteria, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Order> orderPage = orderRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (orderCriteria.getId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("id"),orderCriteria.getId()));
            }
            if (orderCriteria.getStatus() != null){
                predicateList.add(criteriaBuilder.equal(root.get("status"),orderCriteria.getStatus()));
            }
            if (orderCriteria.getUserId() != null){
                predicateList.add(criteriaBuilder.equal(root.get("userId"),orderCriteria.getUserId()));
            }
            if (orderCriteria.getOrderCode() != null){
                predicateList.add(criteriaBuilder.equal(root.get("orderCode"),orderCriteria.getOrderCode()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

        } , pageable);
        return orderMapper.modelToDtos(orderPage);
    }

    @Override
    public ResponseOrderDto addOrder(RequestOrderDto requestOrderDto) {
        String generatedCodeOrder= "ORD" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Order orderToSave = orderMapper.dtoToModel(requestOrderDto);
        orderToSave.setOrderCode(generatedCodeOrder);
        Optional<Users> user= userRepo.findById(requestOrderDto.getUserId());
        orderToSave.setUser(user.get());

        Optional<Order> existingOrder = orderRepo.findByOrderCode(orderToSave.getOrderCode());
        if (existingOrder.isPresent()) {
            throw new EntityAlreadyExisteException("Order already exists with id: " + requestOrderDto.getId());
        }
        List<ResponseOrderDetailsDto> responseOrderDetailsDtos = new ArrayList<>();
        double totalPrice = 0;
        for (RequestOrderDetailsDto orderDetails : requestOrderDto.getOrderDetailsList()) {
            totalPrice = totalPrice + (orderDetails.getUnitPrice()*orderDetails.getQuantity());
        }
        System.out.println(totalPrice);
        System.out.println(orderToSave.getTotalPrice());
        if (totalPrice == orderToSave.getTotalPrice()){

            Order savedOrder = orderRepo.save(orderToSave);
            for (RequestOrderDetailsDto orderDetails : requestOrderDto.getOrderDetailsList()) {
                orderDetails.setOrderId(savedOrder.getId());
                ResponseOrderDetailsDto orderDetails1 = orderDetailsService.addOrderDetails(orderDetails);
                responseOrderDetailsDtos.add(orderDetails1);
            }
            ResponseOrderDto responseOrderDto = orderMapper.modelToDto(savedOrder);
            responseOrderDto.setOrderDetails(responseOrderDetailsDtos);
            return responseOrderDto ;
        }else {
            throw new EntityAlreadyExisteException("Total price error");
        }


    }

    @Override
    public ResponseOrderDto UpdateOrder(RequestOrderDto requestOrderDto) {
        Optional<Order> existingOrder = orderRepo.findByOrderCode(requestOrderDto.getOrderCode());
        if (existingOrder.isEmpty()){
            throw new EntityNotFoundException("Order Not Found   ");
        }
        List<ResponseOrderDetailsDto> responseOrderDetailsDtos = new ArrayList<>();
        double totalPrice = 0;
        for (RequestOrderDetailsDto orderDetails : requestOrderDto.getOrderDetailsList()) {
            totalPrice = totalPrice + (orderDetails.getUnitPrice()*orderDetails.getQuantity());
        }
        Order orderToUpdate = orderMapper.dtoToModel(requestOrderDto);
        orderToUpdate.setOrderCode(requestOrderDto.getOrderCode());
        orderToUpdate.setUser(userRepo.findById(requestOrderDto.getUserId()).get());
        if (totalPrice == orderToUpdate.getTotalPrice()){
            Order updatedOrder= orderRepo.save(orderToUpdate);
            for (RequestOrderDetailsDto orderDetails : requestOrderDto.getOrderDetailsList()) {
                orderDetails.setOrderId(updatedOrder.getId());
                ResponseOrderDetailsDto orderDetails1 = orderDetailsService.addOrderDetails(orderDetails);
                responseOrderDetailsDtos.add(orderDetails1);
            }
            ResponseOrderDto responseOrderDto = orderMapper.modelToDto(updatedOrder);
            responseOrderDto.setOrderDetails(responseOrderDetailsDtos);
            return responseOrderDto ;
        }else {
            throw new EntityAlreadyExisteException("Total price error");
        }

    }

    @Override
    public void deleteOrder(long id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isEmpty()){
            throw new EntityNotFoundException("Order Not Found ID :  "+id);
        }
        orderRepo.deleteById(id);
    }
}
