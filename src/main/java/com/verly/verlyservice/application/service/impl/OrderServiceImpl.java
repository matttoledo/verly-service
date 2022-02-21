package com.verly.verlyservice.application.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.verly.verlyservice.application.model.Cart;
import com.verly.verlyservice.application.model.Item;
import com.verly.verlyservice.application.model.Order;
import com.verly.verlyservice.application.model.product.Product;
import com.verly.verlyservice.application.repository.OrderRepository;
import com.verly.verlyservice.application.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionMessage.ItemsBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.SystemPropertyUtils;


import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAll(){return orderRepository.findAll();
    }

    public Order create(Order order){
        
        ArrayList<Long> productIds = new ArrayList<>();
        // ArrayList<Item> products = util.readProducts(order);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        // products.stream().forEach(product ->{
        //     productIds.add(product.getProductId());
        // });

        log.info("buscar os productIds na interface de produtos");
        log.info("verificar a quantidade e somar o preço de todos os produtos");

        // order.setCreatedDate(LocalDateTime.now());
        // order.setDeliveryDate(LocalDateTime.now().plusDays(15));
        // order.setProfit(order.getPrice()-order.getCost());
        // order.setDebt(order.getPrice()-order.getPaid());
    
        return orderRepository.save(order);
    }

    public void calculateOrderPrice(Order order){
        Double price;
        Map<String,Object> items = order.getProducts();
        
        for(Map.Entry<String,Object> entry : items.entrySet()){
                System.out.println("Entry: "+ entry);
        }

    }


    public void delete(Order order){
        orderRepository.delete(order);
    }

    @Override
    public Order edit(Order order) {
        return orderRepository.save(order);
    }


}
