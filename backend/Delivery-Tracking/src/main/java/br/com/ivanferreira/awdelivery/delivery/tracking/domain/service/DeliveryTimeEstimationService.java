package br.com.ivanferreira.awdelivery.delivery.tracking.domain.service;

import br.com.ivanferreira.awdelivery.delivery.tracking.domain.model.ContactPoint;

public interface DeliveryTimeEstimationService {
    DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver);
}
