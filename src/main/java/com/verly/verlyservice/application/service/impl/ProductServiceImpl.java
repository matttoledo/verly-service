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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private Double incolorPrice = 25.00;
    private Double fumePrice = 25.00;
    private Double verdePrice = 50.00;

    private Double porta2FGain = 0.5;
    private Double porta4FGain = 0.6;
    private Double portaOpenGain = 0.4;
    private Double box2FGain = 0.3;
    private Double box4FGain = 0.3;
    private Double boxOpenGain = 0.4;
    private Double janela2FGain = 0.7;
    private Double janela4fGain = 0.7;
    private Double bascula2FGain = 0.5;
    private Double bascula4FGain = 0.5;

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
            calculatePriceAndCost(productDTO);
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

    private void calculatePriceAndCost(ProductDTO productDTO) {
        Double colorPrice = 0.00;
        productDTO.setMeasure(productDTO.getWidth() * productDTO.getHeight() / 10000);

        if(productDTO.getCategory().equals(ProductCategory.VIDRO_TEMPERADO.toString())) {
            if (productDTO.getColor().equals(ProductColor.INCOLOR.toString()))
                colorPrice = incolorPrice;
            else if (productDTO.getColor().equals(ProductColor.FUME.toString()))
                colorPrice = fumePrice;
            else if (productDTO.getColor().equals(ProductColor.VERDE.toString()))
                colorPrice = verdePrice;

            if (productDTO.getType().equals(ProductType.BOX.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.DUAS.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValueBox2F);
                    productDTO.setPrice(productDTO.getCost() + box2FGain * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if (productDTO.getSheets().equals(ProductSheets.QUATRO.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValueBox4F);
                    productDTO.setPrice(productDTO.getCost() + box4FGain * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.OPEN.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValueBoxOpen);
                    productDTO.setPrice(productDTO.getCost() + boxOpenGain * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }

            if (productDTO.getType().equals(ProductType.JANELA.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.DUAS.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValueJanela2F);
                    productDTO.setPrice(productDTO.getCost() + janela2FGain * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.QUATRO.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValueJanela4F);
                    productDTO.setPrice(productDTO.getCost() + janela4fGain * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }
            if(productDTO.getType().equals(ProductType.PORTA.toString())){
                if(productDTO.getSheets().equals(ProductSheets.DUAS.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValuePorta2F);
                    productDTO.setPrice(productDTO.getCost() + janela2FGain * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.QUATRO.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValuePorta4F);
                    productDTO.setPrice(productDTO.getCost() + porta4FGain * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.OPEN.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + laborValuePortaOpen);
                    productDTO.setPrice(productDTO.getCost() + portaOpenGain * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }

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
