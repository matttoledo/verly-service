package com.verly.verlyservice.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_cost", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@EnableAutoConfiguration
public class TemperedGlassCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double colorLessCost;

    private Double smokedCost;

    private Double greenCost;

    private Double twoSheetsDoorGain;

    private Double fourSheetsDoorGain;

    private Double oneSheetDoorGain;

    private Double twoSheetsBoxGain;

    private Double fourSheetsBoxGain;

    private Double oneSheetBoxGain;

    private Double twoSheetsWindowGain;

    private Double fourSheetsWindowGain;

    private Double twoSheetsBoxLaborValue;

    private Double fourSheetsBoxLaborValue;

    private Double oneSheetWindowLaborValue;

    private Double twoSheetsWindowLaborValue;

    private Double fourSheetsWindowLaborValue;

    private Double oneSheetDoorLaborValue;

    private Double twoSheetsDoorLaborValue;

    private Double threeSheetsDoorLaborValue;

    private Double fourSheetDoorLaborValue;
}
