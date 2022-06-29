package com.verly.verlyservice.application.service.impl;

import com.verly.verlyservice.application.model.product.Product;
import com.verly.verlyservice.application.model.product.ProductDTO;
import com.verly.verlyservice.application.model.product.enums.ProductCategory;
import com.verly.verlyservice.application.model.product.enums.ProductColor;
import com.verly.verlyservice.application.model.product.enums.ProductSheets;
import com.verly.verlyservice.application.model.product.enums.ProductType;
import com.verly.verlyservice.application.repository.ProductRepository;
import com.verly.verlyservice.application.service.ProductCostService;
import com.verly.verlyservice.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.decimal4j.util.DoubleRounder;
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
    private final ProductCostService productCostService;

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
                colorPrice = 50.00;
            else if (productDTO.getColor().equals(ProductColor.FUME.toString()))
                colorPrice = 60.00;
            else if (productDTO.getColor().equals(ProductColor.VERDE.toString()))
                colorPrice = 70.00;

            if (productDTO.getType().equals(ProductType.BOX.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.DUAS.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCostService.recoverCost().getLaborValueBox2F());
                    productDTO.setPrice(productDTO.getCost() + productCostService.recoverCost().getBox2FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if (productDTO.getSheets().equals(ProductSheets.QUATRO.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCostService.recoverCost().getLaborValueBox4F());
                    productDTO.setPrice(productDTO.getCost() + productCostService.recoverCost().getBox4FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.OPEN.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCostService.recoverCost().getLaborValueBoxOpen());
                    productDTO.setPrice(productDTO.getCost() + productCostService.recoverCost().getBoxOpenGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }

            if (productDTO.getType().equals(ProductType.JANELA.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.DUAS.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCostService.recoverCost().getLaborValueJanela2F());
                    productDTO.setPrice(productDTO.getCost() + productCostService.recoverCost().getJanela2FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.QUATRO.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCostService.recoverCost().getLaborValueJanela4F());
                    productDTO.setPrice(productDTO.getCost() + productCostService.recoverCost().getJanela4fGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }
            if(productDTO.getType().equals(ProductType.PORTA.toString())){
                if(productDTO.getSheets().equals(ProductSheets.DUAS.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCostService.recoverCost().getLaborValuePorta2F());
                    productDTO.setPrice(productDTO.getCost() + productCostService.recoverCost().getPorta2FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.QUATRO.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCostService.recoverCost().getLaborValuePorta4F());
                    productDTO.setPrice(productDTO.getCost() + productCostService.recoverCost().getPorta4FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.OPEN.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + productCostService.recoverCost().getLaborValuePortaOpen());
                    productDTO.setPrice(productDTO.getCost() + productCostService.recoverCost().getPortaOpenGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }

        }
        productDTO.setProfit(DoubleRounder.round(productDTO.getProfit(),2));
        productDTO.setCost(DoubleRounder.round(productDTO.getCost(),2));

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
