package com.verly.verlyservice.application.service.impl;

import com.verly.verlyservice.application.model.ProductCost;
import com.verly.verlyservice.application.model.product.Product;
import com.verly.verlyservice.application.model.product.ProductDTO;
import com.verly.verlyservice.application.model.product.enums.ProductCategory;
import com.verly.verlyservice.application.model.product.enums.ProductColor;
import com.verly.verlyservice.application.model.product.enums.ProductSheets;
import com.verly.verlyservice.application.model.product.enums.ProductType;
import com.verly.verlyservice.application.repository.ProductCostRepository;
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
    private final ProductCostRepository productCostRepository;

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
        ProductCost productCost = new ProductCost();

        productDTO.setMeasure(productDTO.getWidth() * productDTO.getHeight() / 10000);

        if(productDTO.getCategory().equals(ProductCategory.VIDRO_TEMPERADO.toString())) {
            if (productDTO.getColor().equals(ProductColor.INCOLOR.toString()))
                colorPrice = productCost.getIncolorPrice();
            else if (productDTO.getColor().equals(ProductColor.FUME.toString()))
                colorPrice = productCost.getFumePrice();
            else if (productDTO.getColor().equals(ProductColor.VERDE.toString()))
                colorPrice = productCost.getVerdePrice();

            if (productDTO.getType().equals(ProductType.BOX.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.DUAS.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCost.getLaborValueBox2F());
                    productDTO.setPrice(productDTO.getCost() + productCost.getBox2FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if (productDTO.getSheets().equals(ProductSheets.QUATRO.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCost.getLaborValueBox4F());
                    productDTO.setPrice(productDTO.getCost() + productCost.getBox4FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.OPEN.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCost.getLaborValueBoxOpen());
                    productDTO.setPrice(productDTO.getCost() + productCost.getBoxOpenGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }

            if (productDTO.getType().equals(ProductType.JANELA.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.DUAS.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCost.getLaborValueJanela2F());
                    productDTO.setPrice(productDTO.getCost() + productCost.getJanela2FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.QUATRO.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCost.getLaborValueJanela4F());
                    productDTO.setPrice(productDTO.getCost() + productCost.getJanela4fGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }
            if(productDTO.getType().equals(ProductType.PORTA.toString())){
                if(productDTO.getSheets().equals(ProductSheets.DUAS.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCost.getLaborValuePorta2F());
                    productDTO.setPrice(productDTO.getCost() + productCost.getPorta2FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.QUATRO.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCost.getLaborValuePorta4F());
                    productDTO.setPrice(productDTO.getCost() + productCost.getPorta4FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.OPEN.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCost.getLaborValuePortaOpen());
                    productDTO.setPrice(productDTO.getCost() + productCost.getPortaOpenGain() * productDTO.getCost());
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
