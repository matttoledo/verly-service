package com.verly.verlyservice.application.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.verly.verlyservice.application.model.Customer;
import com.verly.verlyservice.application.model.Order;
import com.verly.verlyservice.application.repository.CustomerRepository;
import com.verly.verlyservice.application.repository.OrderRepository;
import com.verly.verlyservice.application.service.CustomerService;
import com.verly.verlyservice.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAll(){return orderRepository.findAll();
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }


    public void delete(Order order){
        orderRepository.delete(order);
    }


}
