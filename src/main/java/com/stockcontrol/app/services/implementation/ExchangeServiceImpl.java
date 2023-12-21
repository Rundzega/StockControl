package com.stockcontrol.app.services.implementation;

import com.stockcontrol.app.domain.stock.Exchange;
import com.stockcontrol.app.repositories.ExchangeRepository;
import com.stockcontrol.app.services.ExchangeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private final ExchangeRepository exchangeRepository;

    @Override
    public Exchange create(Exchange exchange) {
        if (exchangeRepository.findByMic(exchange.getMic()).isPresent()) {
            throw new DataIntegrityViolationException("Exchange Mic already registered.");
        }
        return exchangeRepository.save(exchange);
    }

    @Override
    public Exchange findByMic(String mic) {
        return exchangeRepository.findByMic(mic).orElseThrow(() ->  {
            throw new EntityNotFoundException("Exchange with mic " + mic + " not found.");
        });
    }

    @Override
    public Collection<Exchange> findAll() {
        return exchangeRepository.findAll();
    }

    @Override
    public Collection<Exchange> findByCountry(String country) {
        return exchangeRepository.findByCountry(country);
    }

    @Override
    public Exchange update(Exchange exchange, String id) {
        return exchangeRepository.findById(id).map(dbExchange -> {
            dbExchange.setCurrency(exchange.getCurrency());
            dbExchange.setMic(exchange.getMic());
            dbExchange.setCountry(exchange.getCountry());
            return exchangeRepository.save(dbExchange);
        }).orElseGet(() -> {
            exchange.setId(id);
            return exchangeRepository.save(exchange);
        });
    }

    @Override
    public void delete(String id) {
        exchangeRepository.deleteById(id);
    }
}
