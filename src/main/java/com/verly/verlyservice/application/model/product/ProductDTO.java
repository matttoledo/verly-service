package com.verly.verlyservice.application.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.verly.verlyservice.application.model.product.enums.ProductCategory;
import com.verly.verlyservice.application.model.product.enums.ProductColor;
import com.verly.verlyservice.application.model.product.enums.ProductSheets;
import com.verly.verlyservice.application.model.product.enums.ProductType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    private ProductCategory category;

    private ProductType type;

    private ProductSheets sheets;

    private Double width;

    private Double height;

    private Double weight;

    private Double measure;

    private ProductColor color;

    private Double cost;

    private Double price;

    private Double profit;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdateDate;
}
