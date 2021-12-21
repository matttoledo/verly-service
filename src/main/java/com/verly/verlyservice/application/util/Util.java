package com.verly.verlyservice.application.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.verly.verlyservice.application.model.Item;
import com.verly.verlyservice.application.model.Order;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Util {

    private final Gson gson = new Gson();

    public ArrayList<Item> readProducts(Order order){

        Type productsRaw = new TypeToken<ArrayList<Item>>() {}.getType();

        ArrayList<Item> products = gson.fromJson(order.getProducts(), productsRaw);

        if(Objects.nonNull(products)){
            log.info("products", products.toString());
            return products;
        }

        return new ArrayList<>();

    }


    
}
