package com.verly.verlyservice.application.service.impl;

import com.verly.verlyservice.application.model.product.Product;
import com.verly.verlyservice.application.model.product.ProductDTO;
import com.verly.verlyservice.application.model.product.enums.ProductCategory;
import com.verly.verlyservice.application.model.product.enums.ProductColor;
import com.verly.verlyservice.application.model.product.enums.ProductSheets;
import com.verly.verlyservice.application.model.product.enums.ProductType;
import com.verly.verlyservice.application.repository.ProductRepository;
import com.verly.verlyservice.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private Double incolorPrice = 25.00;
    private Double fumePrice = 25.00;
    private Double verdePrice = 50.00;

    private Double laborValueBox2F= 10.00;
    private Double laborValueBox4F= 15.00;
    private Double laborValueBoxOpen= 20.00;
    private Double laborValuePorta2F= 20.00;
    private Double laborValuePorta4F= 25.00;
    private Double laborValuePortaOpen= 30.00;
    private Double laborValueJanela2F= 20.00;
    private Double laborValueJanela4F= 25.00;
    private Double laborValueJanelaOpen= 30.00;


    public List<ProductDTO> findAll() {
        var products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product : products){
            var productDTO = productToProductDTO(product);
            calculateCost(productDTO);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    private ProductDTO productToProductDTO (Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(product.getCategory());
        productDTO.setType(product.getType());
        productDTO.setSheets(product.getSheets());
        productDTO.setWidth(product.getWidth());
        productDTO.setHeight(product.getHeight());
        productDTO.setWeight(product.getWeight());
        productDTO.setColor(product.getColor());

        return productDTO;
    }

    private Product productDTOToProduct (ProductDTO productDTO){
        Product product = new Product();
        product.setCategory(productDTO.getCategory());
        product.setType(productDTO.getType());
        product.setSheets(productDTO.getSheets());
        product.setWidth(productDTO.getWidth());
        product.setHeight(productDTO.getHeight());
        product.setWeight(productDTO.getWeight());
        product.setColor(productDTO.getColor());

        return product;
    }

    private void calculateCost(ProductDTO productDTO) {
        Double colorPrice = 0.00;

        if(productDTO.getCategory().equals(ProductCategory.VIDRO_TEMPERADO)) {
            if (productDTO.getColor().equals(ProductColor.INCOLOR))
                colorPrice = incolorPrice;
            else if (productDTO.getColor().equals(ProductColor.FUME))
                colorPrice = fumePrice;
            else if (productDTO.getColor().equals(ProductColor.VERDE))
                colorPrice = verdePrice;

            if (productDTO.getType().equals(ProductType.BOX)) {
                if (productDTO.getSheets().equals(ProductSheets.DUAS))
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValueBox2F);
                if (productDTO.getSheets().equals(ProductSheets.QUATRO))
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValueBox4F);
            }

            if (productDTO.getType().equals(ProductType.JANELA)) {
                if (productDTO.getSheets().equals(ProductSheets.DUAS))
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValueJanela2F);

            }
            if (productDTO.getColor().equals(ProductColor.INCOLOR))
                productDTO.setCost(productDTO.getMeasure() * incolorPrice);

        }
    }

    public void delete(ProductDTO productDTO) {
        productRepository.delete(productDTOToProduct(productDTO));
    }

    @Override
    public Product create(ProductDTO productDTO) {
        var product = productDTOToProduct(productDTO);

        product.setCreatedDate(LocalDateTime.now());

        return productRepository.save(product);
    }

    @Override
    public Product edit(ProductDTO productDTO) {
        var product = productRepository.getById(productDTO.getId());
        var productDTO2 = productToProductDTO(product);
        return productDTOToProduct(productDTO2);
    }



    public ArrayList<Product> findProductsByproductIds(ArrayList<Long> productIds){
        
        // ArrayList<Product> products = new ArrayList(); 
        // productIds.stream().forEach(product ->{
        //     productIds.add(product);
        // });

        return (ArrayList<Product>) productRepository.findAllById(productIds);
    }


}
