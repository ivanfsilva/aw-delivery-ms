package br.com.ivanferreira.awdelivery.delivery.tracking.domain.repository;

import br.com.ivanferreira.awdelivery.delivery.tracking.domain.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository
        extends JpaRepository<Delivery, UUID> {
}
