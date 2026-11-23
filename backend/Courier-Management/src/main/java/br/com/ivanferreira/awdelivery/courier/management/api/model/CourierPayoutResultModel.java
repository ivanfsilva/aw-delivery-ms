package br.com.ivanferreira.awdelivery.courier.management.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourierPayoutResultModel {
    private BigDecimal payoutFee;
}
