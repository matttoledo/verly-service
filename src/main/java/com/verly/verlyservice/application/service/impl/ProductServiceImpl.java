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
    private Double sumCost(ProductDTO product, Double colorPrice, Double laborValue){
        return (product.getMeasure() * colorPrice) +
//                getProductByKey(product.getKit()).getCost()+
                laborValue;
    }

    private void resolveValues(ProductDTO product, Double colorPrice, Double laborValue, Double gain){
        product.setCost(sumCost(product, colorPrice, laborValue));
        product.setPrice(product.getCost() + gain * product.getCost());
        product.setProfit(product.getPrice() - product.getCost());

    }

    private void calculatePriceAndCost(ProductDTO productDTO) {
        Double colorPrice = 0.00;
        //seta a medida
        productDTO.setMeasure(productDTO.getWidth() * productDTO.getHeight() / 10000);

        //verifica recupera o valor do vidro temperado
        if (productDTO.getCategory().equals(ProductCategory.VIDRO_TEMPERADO.toString())) {
            if (productDTO.getColor().equals(ProductColor.INCOLOR.toString()))
                colorPrice = temperedGlassCostService.recover().getColorLessCost();
            else if (productDTO.getColor().equals(ProductColor.FUME.toString()))
                colorPrice = temperedGlassCostService.recover().getSmokedCost();
            else if (productDTO.getColor().equals(ProductColor.VERDE.toString()))
                colorPrice = temperedGlassCostService.recover().getGreenCost();

            //BOX
            if (productDTO.getType().equals(ProductType.BOX.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.TWO.toString()))
                    resolveValues(productDTO, colorPrice,
                            temperedGlassCostService.recover().getTwoSheetsBoxLaborValue(),
                            temperedGlassCostService.recover().getTwoSheetsBoxGain());

                if (productDTO.getSheets().equals(ProductSheets.FOUR.toString()))
                    resolveValues(productDTO, colorPrice,
                            temperedGlassCostService.recover().getFourSheetDoorLaborValue(),
                            temperedGlassCostService.recover().getFourSheetsBoxGain());

                if (productDTO.getSheets().equals(ProductSheets.ONE.toString()))
                    resolveValues(productDTO, colorPrice,
                            temperedGlassCostService.recover().getOneSheetDoorLaborValue(),
                            temperedGlassCostService.recover().getOneSheetBoxGain());

            }
            //JANELA
            if (productDTO.getType().equals(ProductType.JANELA.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.TWO.toString()))
                    resolveValues(productDTO, colorPrice,
                            temperedGlassCostService.recover().getTwoSheetsWindowLaborValue(),
                            temperedGlassCostService.recover().getTwoSheetsWindowGain());

                if (productDTO.getSheets().equals(ProductSheets.FOUR.toString()))
                    resolveValues(productDTO, colorPrice,
                            temperedGlassCostService.recover().getFourSheetsWindowLaborValue(),
                            temperedGlassCostService.recover().getFourSheetsWindowGain());
            }

            //PORTA
            if (productDTO.getType().equals(ProductType.PORTA.toString())) {
                if (productDTO.getSheets().equals(ProductSheets.TWO.toString())) {
                    resolveValues(productDTO, colorPrice,
                            temperedGlassCostService.recover().getTwoSheetsDoorLaborValue(),
                            temperedGlassCostService.recover().getTwoSheetsDoorGain());

                    if (productDTO.getSheets().equals(ProductSheets.FOUR.toString()))
                        resolveValues(productDTO, colorPrice,
                                temperedGlassCostService.recover().getFourSheetDoorLaborValue(),
                                temperedGlassCostService.recover().getFourSheetsDoorGain());
                    if (productDTO.getSheets().equals(ProductSheets.ONE.toString()))
                        resolveValues(productDTO, colorPrice,
                                temperedGlassCostService.recover().getOneSheetDoorLaborValue(),
                                temperedGlassCostService.recover().getOneSheetDoorGain());
                }
            }

            if (Objects.isNull(productDTO.getProfit()))
                productDTO.setProfit(666.666);
            if (Objects.isNull(productDTO.getCost()))
                productDTO.setCost(666.666);
            productDTO.setProfit(DoubleRounder.round(productDTO.getProfit(), 2));
            productDTO.setCost(DoubleRounder.round(productDTO.getCost(), 2));

        }
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
