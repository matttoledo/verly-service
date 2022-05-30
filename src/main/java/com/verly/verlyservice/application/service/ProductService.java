package com.verly.verlyservice.application.service;

import java.util.ArrayList;
import java.util.List;

import com.verly.verlyservice.application.model.product.Product;
import com.verly.verlyservice.application.model.product.ProductDTO;

public interface ProductService {

    List<ProductDTO> findAll();

    Product create(ProductDTO product);

    Product edit(ProductDTO product);

    void delete(ProductDTO product);

    ArrayList<Product> findProductsByproductIds(ArrayList<Long> productIds);


}
