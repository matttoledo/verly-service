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
@Table(name = "product cost", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@EnableAutoConfiguration
public class ProductCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double incolorPrice;

    private Double fumePrice;

    private Double verdePrice;

    private Double porta2FGain;

    private Double porta4FGain;

    private Double portaOpenGain;

    private Double box2FGain;

    private Double box4FGain;

    private Double boxOpenGain;

    private Double janela2FGain;

    private Double janela4fGain;

    private Double bascula2FGain;

    private Double bascula4FGain;

    private Double laborValueBox2F;

    private Double laborValueBox4F;

    private Double laborValueBoxOpen;

    private Double laborValuePorta2F;

    private Double laborValuePorta4F;

    private Double laborValuePortaOpen;

    private Double laborValueJanela2F;

    private Double laborValueJanela4F;

    private Double laborValueJanelaOpen;
}
