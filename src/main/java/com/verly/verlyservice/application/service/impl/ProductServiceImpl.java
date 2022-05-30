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


    public List<Product> findAll() {
        var products = productRepository.findAll();

    }

    public Double calculateLaborValueBySheetsAndType(ProductDTO productDTO) {
        if(productDTO.getType().equals(ProductType.BOX)){
            if(productDTO.getSheets().equals(ProductSheets.DUAS)){
                return laborValueBox2F;
            }
            else if(productDTO.getSheets().equals(ProductSheets.QUATRO)){

            }
        }
        if(productDTO.getType().equals(ProductType.JANELA)){
            if(productDTO.getSheets().equals(ProductSheets.DUAS)){
            }
            else if(productDTO.getSheets().equals(ProductSheets.QUATRO)){

            }
        }
    }

    private ProductDTO calculatePriceAndCost(Product product) {
        var productDTO = new ProductDTO();
        Double colorPrice = 0.00;
        if(product.getCategory().equals(ProductCategory.VIDRO_TEMPERADO)){
            if(product.getColor().equals(ProductColor.INCOLOR))
                colorPrice= incolorPrice;
            else if(product.getColor().equals(ProductColor.FUME))
                colorPrice = fumePrice;
            else if(product.getColor().equals(ProductColor.VERDE))
                colorPrice = verdePrice;

            if(product.getType().equals(ProductType.BOX)){
                if(product.getSheets().equals(ProductSheets.DUAS)){
                    productDTO.setCost(product.getMeasure()*colorPrice + laborValueBox2F);
                    productDTO.setPrice(verdePrice + laborValueBox2F);
                }
                calculateLaborValueBySheetsAndType(productDTO);
            }
            if(product.getColor().equals(ProductColor.INCOLOR))
                productDTO.setCost(product.getMeasure()*incolorPrice);

        }



    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product create(ProductDTO product) {

        product.setCreatedDate(LocalDateTime.now());

        // transformando em m²
        product.setMeasure(product.getHeight() * product.getWidth() / 10000);

        // setando o custo do produto buscando o valor do m²
        product.setCost(product.getMeasure() * 25);

        return productRepository.save(product);
    }

    @Override
    public ProductDTO edit(ProductDTO product) {
        // TODO Auto-generated method stub
        return null;
    }



    public ArrayList<Product> findProductsByproductIds(ArrayList<Long> productIds){
        
        // ArrayList<Product> products = new ArrayList(); 
        // productIds.stream().forEach(product ->{
        //     productIds.add(product);
        // });

        return (ArrayList<Product>) productRepository.findAllById(productIds);
    }


}
