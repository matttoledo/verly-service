package com.verly.verlyservice.application.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    private Long id;

    private String key;

    private String category;

    private String type;

    private String sheets;

    private String kit;

    private Double width;

    private Double height;

    private Double weight;

    private Double measure;

    private String color;

    private Double cost;

    private Double price;

    private Double profit;

    private String laborValueKey;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdateDate;
}
