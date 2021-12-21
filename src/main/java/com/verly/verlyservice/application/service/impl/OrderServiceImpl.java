package com.verly.verlyservice.application.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.verly.verlyservice.application.model.Cart;
import com.verly.verlyservice.application.model.Item;
import com.verly.verlyservice.application.model.Order;
import com.verly.verlyservice.application.model.product.Product;
import com.verly.verlyservice.application.repository.OrderRepository;
import com.verly.verlyservice.application.service.OrderService;
import com.verly.verlyservice.application.util.Util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionMessage.ItemsBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.SystemPropertyUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final Util util;

    public List<Order> findAll(){return orderRepository.findAll();
    }

    public Order create(Order order){
        
        List<Item> items = util.readProducts(order);
        List<Product> products = new ArrayList<>();
        List<String> productsId = new ArrayList<>();
        Double sumProducts = 0.0001D;
    
        items.stream().forEach(item ->{
            productsId.add(item.getProductId());
            
        });

        log.error(productsId.toString());

        log.error("buscar os productsIds na interface dos produtos");

        System.out.println();
        List<Double> totalPrice = new ArrayList<>();

    
        System.out.println(totalPrice.toString());

    
        return orderRepository.save(order);
    }


    public void delete(Order order){
        orderRepository.delete(order);
    }

    @Override
    public Order edit(Order order) {
        return orderRepository.save(order);
    }


}
