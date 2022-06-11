package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.ProductCost;

import java.util.List;

public interface ProductCostService {

    List<ProductCost> findAll();

    ProductCost create(ProductCost productCost);

    ProductCost edit(ProductCost productCost);

    void delete(Long id);

    ProductCost findOne(Long id);
}
