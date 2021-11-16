package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order save(Order order);

    void delete(Order order);
}


