package br.com.ivanferreira.awdelivery.delivery.tracking.infrastructure.http.client;

import br.com.ivanferreira.awdelivery.delivery.tracking.domain.service.CourierPayoutCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService {

    private final CourierAPIClient courierAPIClient;

    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {
        var input = new CourierPayoutCalculationInput(distanceInKm);
        var resultModel = courierAPIClient.payoutCalculation(input);
        return resultModel.getPayoutFee();
    }
}
