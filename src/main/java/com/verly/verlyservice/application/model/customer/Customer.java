package com.verly.verlyservice.application.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.verly.verlyservice.application.util.HashMapConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customer", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EnableAutoConfiguration
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> phone;

    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> address;
    
    private Boolean defaulter;

    private LocalDateTime createdAt;

}
