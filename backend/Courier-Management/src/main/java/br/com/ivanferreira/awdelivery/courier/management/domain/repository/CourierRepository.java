package br.com.ivanferreira.awdelivery.courier.management.domain.repository;

import br.com.ivanferreira.awdelivery.courier.management.domain.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourierRepository
        extends JpaRepository<Courier, UUID> {
}
