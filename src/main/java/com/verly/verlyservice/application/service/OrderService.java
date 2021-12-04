package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order create(Order order);

    Order edit (Order order);

    void delete(Order order);
}


