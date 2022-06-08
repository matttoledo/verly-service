package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.ProductCost;

import java.util.List;
import java.util.ArrayList;

public interface ProductCostService {

    List<ProductCost> findAll();

    ProductCost create(ProductCost productCost);

    ProductCost edit(ProductCost productCost);

    void delete(ProductCost productCost);

    ArrayList<ProductCost> findProductsCostById(ArrayList<Long> productCostId);
}
