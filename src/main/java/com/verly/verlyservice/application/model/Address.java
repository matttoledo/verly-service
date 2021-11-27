package com.verly.verlyservice.application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String cep;

    private String street;

    private String number;

    private String district;

    private String city;

    private String state;

    private String complement;

}
