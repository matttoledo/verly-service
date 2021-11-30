package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product create(Product product);

    Product edit(Product product);

    void delete(Product product);
}
