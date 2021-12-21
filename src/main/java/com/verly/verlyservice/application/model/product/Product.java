package com.verly.verlyservice.application.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.verly.verlyservice.application.model.product.enums.ProductCategory;
import com.verly.verlyservice.application.model.product.enums.ProductColor;
import com.verly.verlyservice.application.model.product.enums.ProductType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product", schema = "public")
@JsonIgnoreProperties(ignoreUnknown = true)
@EnableAutoConfiguration
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String type;

    private String sheets;

    private Double width;

    private Double height;

    private Double measure;

    private String color;

    private Double cost;

    private Double price;

    private Double profit;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdateDate;

}