package com.verly.verlyservice.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.verly.verlyservice.application.model.enums.ProductCategory;
import com.verly.verlyservice.application.model.enums.ProductColor;
import com.verly.verlyservice.application.model.enums.ProductType;

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

    private Long width;

    private Long height;

    private Long measure;

    private String color;

    private Long cost;

    private Long price;

    private Long profit;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdateDate;

}
