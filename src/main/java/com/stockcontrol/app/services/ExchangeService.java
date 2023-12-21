package com.stockcontrol.app.services;

import com.stockcontrol.app.domain.stock.Exchange;
import org.springframework.hateoas.CollectionModel;

import java.util.Collection;

public interface ExchangeService {
    public Exchange create(Exchange exchange);

    public Exchange findByMic(String mic);

    public Collection<Exchange> findAll();

    public Collection<Exchange> findByCountry(String country);

    public Exchange update(Exchange exchange, String id);

    public void delete(String id);
}
