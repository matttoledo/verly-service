package com.verly.verlyservice.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    Integer id;

    String name;

    String cpf;

    Address address;

    List<String> phone;

    Boolean defaulter;

    @CreatedDate
    Date createdAt;



}
