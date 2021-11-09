package com.verly.verlyservice.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer", schema = "public")
@JsonIgnoreProperties(ignoreUnknown = true)
@EnableAutoConfiguration

public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    String name;

    String cpf;

    String address;

    String phone;

    Boolean defaulter;

    @CreatedDate
    Date createdAt;


}
