package com.khaliullov.love_nail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaliullov.love_nail.api.OrderRequest;
import com.khaliullov.love_nail.api.OrderResponse;
import com.khaliullov.love_nail.repo.OrderRequestRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRequestRepo orderRequestRepo;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderResponse createOrder(String request) {
        OrderResponse orderResponse;
        OrderRequest orderRequest;
        try {
            orderRequest = objectMapper.readValue(request, OrderRequest.class);
        } catch (JsonProcessingException e) {
            orderResponse =  new OrderResponse(new RuntimeException(e).toString());
            throw new RuntimeException(e);
        }
        if (orderRequest != null){
            orderRequestRepo.save(orderRequest);
            orderResponse = new OrderResponse(HttpStatus.OK.toString());
        } else orderResponse = new OrderResponse(HttpStatus.BAD_REQUEST.toString());

        return orderResponse;
    }

    Object object = new Object();

}
