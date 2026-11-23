package br.com.ivanferreira.awdelivery.courier.management.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourierPayoutCalculationInput {
    private Double distanceInKm;
}
