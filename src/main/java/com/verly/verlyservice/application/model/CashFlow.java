package com.verly.verlyservice.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cash-flow", schema = "public")
@JsonIgnoreProperties(ignoreUnknown = true)
@EnableAutoConfiguration
public class CashFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Long cashIn;

    private Long cashOut;

    private Long orderId;

    private String person;

    @CreatedDate
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
