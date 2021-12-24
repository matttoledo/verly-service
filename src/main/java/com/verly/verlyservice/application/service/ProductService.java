package com.verly.verlyservice.application.service;

import java.util.ArrayList;
import java.util.List;

import com.verly.verlyservice.application.model.product.Product;

public interface ProductService {

    List<Product> findAll();

    Product create(Product product);

    Product edit(Product product);

    void delete(Product product);

    ArrayList<Product> findProductsByproductIds(ArrayList<Long> productIds);


}
