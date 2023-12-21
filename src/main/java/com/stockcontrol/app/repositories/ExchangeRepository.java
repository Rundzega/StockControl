package com.stockcontrol.app.repositories;

import com.stockcontrol.app.domain.stock.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Exchange, String> {

    Optional<Exchange> findByMic(String mic);

    Collection<Exchange> findByCountry(String country);
}
