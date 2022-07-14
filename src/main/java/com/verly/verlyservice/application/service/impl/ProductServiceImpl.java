package com.verly.verlyservice.application.service.impl;

import com.verly.verlyservice.application.model.product.Product;
import com.verly.verlyservice.application.model.product.ProductDTO;
import com.verly.verlyservice.application.model.product.enums.ProductCategory;
import com.verly.verlyservice.application.model.product.enums.ProductColor;
import com.verly.verlyservice.application.model.product.enums.ProductSheets;
import com.verly.verlyservice.application.model.product.enums.ProductType;
import com.verly.verlyservice.application.repository.ProductRepository;
import com.verly.verlyservice.application.service.TemperedGlassCostService;
import com.verly.verlyservice.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.decimal4j.util.DoubleRounder;
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
    private final TemperedGlassCostService temperedGlassCostService;

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

    private ProductDTO getProductByKey(String key){
        var product = productToProductDTO(productRepository.findByKey(key));
        calculatePriceAndCost(product);
        return product;
    }

    private Double sumCostforBox(ProductDTO product){
        return product
    }

    private void calculatePriceAndCost(ProductDTO productDTO) {
        Double colorPrice = 0.00;
        //seta a medida
        productDTO.setMeasure(productDTO.getWidth() * productDTO.getHeight() / 10000);

        //verifica recupera o valor do vidro temperado
        if(productDTO.getCategory().equals(ProductCategory.VIDRO_TEMPERADO.toString())) {
            if (productDTO.getColor().equals(ProductColor.INCOLOR.toString()))
                colorPrice = temperedGlassCostService.recover().getColorLessCost();
            else if (productDTO.getColor().equals(ProductColor.FUME.toString()))
                colorPrice = temperedGlassCostService.recover().getSmokedCost();
            else if (productDTO.getColor().equals(ProductColor.VERDE.toString()))
                colorPrice = temperedGlassCostService.recover().getGreenCost();

            //BOX
            if (productDTO.getType().equals(ProductType.BOX.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.TWO.toString())) {
                    productDTO.setCost();
                    productDTO.setPrice(productDTO.getCost() + temperedGlassCostService.recover().getTwoSheetsBoxGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if (productDTO.getSheets().equals(ProductSheets.QUATRO.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + temperedGlassCostService.recover().getLaborValueBox4F());
                    productDTO.setPrice(productDTO.getCost() + temperedGlassCostService.recover().getBox4FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.OPEN.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + temperedGlassCostService.recover().getLaborValueBoxOpen());
                    productDTO.setPrice(productDTO.getCost() + temperedGlassCostService.recover().getBoxOpenGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }

            //JANELA
            if (productDTO.getType().equals(ProductType.JANELA.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.DUAS.toString())) {
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + temperedGlassCostService.recover().getLaborValueJanela2F());
                    productDTO.setPrice(productDTO.getCost() + temperedGlassCostService.recover().getJanela2FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.QUATRO.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + temperedGlassCostService.recover().getLaborValueJanela4F());
                    productDTO.setPrice(productDTO.getCost() + temperedGlassCostService.recover().getJanela4fGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }

            //PORTA
            if(productDTO.getType().equals(ProductType.PORTA.toString())){
                if(productDTO.getSheets().equals(ProductSheets.DUAS.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + temperedGlassCostService.recover().getLaborValuePorta2F());
                    productDTO.setPrice(productDTO.getCost() + temperedGlassCostService.recover().getPorta2FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.QUATRO.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + temperedGlassCostService.recover().getLaborValuePorta4F());
                    productDTO.setPrice(productDTO.getCost() + temperedGlassCostService.recover().getPorta4FGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
                if(productDTO.getSheets().equals(ProductSheets.OPEN.toString())){
                    productDTO.setCost(productDTO.getMeasure() * colorPrice + temperedGlassCostService.recover().getLaborValuePortaOpen());
                    productDTO.setPrice(productDTO.getCost() + temperedGlassCostService.recover().getPortaOpenGain() * productDTO.getCost());
                    productDTO.setProfit(productDTO.getPrice() - productDTO.getCost());
                }
            }

        }
        if(Objects.isNull(productDTO.getProfit()))
            productDTO.setProfit(666.666);
        if(Objects.isNull(productDTO.getCost()))
            productDTO.setCost(666.666);
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

        var teste = productRepository.save(product);

        return teste;
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

    private ProductDTO productToProductDTO (Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(product.getCategory());
        productDTO.setType(product.getType());
        productDTO.setSheets(product.getSheets());
        productDTO.setWidth(product.getWidth());
        productDTO.setHeight(product.getHeight());
        productDTO.setWeight(product.getWeight());
        productDTO.setColor(product.getColor());
        productDTO.setKit(product.getKit());
        productDTO.setKey(product.getKey());
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
        product.setKit(productDTO.getKit());
        product.setKey(productDTO.getKey());
        return product;
    }


}
