package com.verly.verlyservice.application.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    String category;

    String type;

    String sheets;

    Long widht;

    Long height;

    String color;
    
}
// widht e heigh estão em cm