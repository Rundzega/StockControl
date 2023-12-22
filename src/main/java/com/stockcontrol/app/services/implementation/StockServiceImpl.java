package com.stockcontrol.app.services.implementation;

import com.stockcontrol.app.domain.stock.Exchange;
import com.stockcontrol.app.domain.stock.Stock;
import com.stockcontrol.app.domain.stock.TaxGroup;
import com.stockcontrol.app.repositories.StockRepository;
import com.stockcontrol.app.services.ExchangeService;
import com.stockcontrol.app.services.StockService;
import com.stockcontrol.app.services.TaxGroupService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired
    private final StockRepository stockRepository;

    @Autowired
    private final ExchangeService exchangeService;

    @Autowired
    private final TaxGroupService taxGroupService;

    @Override
    public Stock create(Stock stock) {
        if (stockRepository.findByTickerAndExchange_Mic(stock.getTicker(), stock.getExchange().getMic()).isPresent()) {
            throw new DataIntegrityViolationException("Stock already registered.");
        }
        Exchange exchange = exchangeService.findByMic(stock.getExchange().getMic());
        stock.setExchange(exchange);
        System.out.println(stock.getTaxGroup().getId());
        TaxGroup taxGroup = taxGroupService.findById(stock.getTaxGroup().getId());
        stock.setTaxGroup(taxGroup);

        return stockRepository.save(stock);
    }

    @Override
    public Collection<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock findById(String id) {
        return stockRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Stock with id " + id + " not found");
        });
    }

    @Override
    public Stock findByTickerAndExchange_Mic(String ticker, String mic) {
        return stockRepository.findByTickerAndExchange_Mic(ticker, mic).orElseThrow(() -> {
            throw new EntityNotFoundException("Stock with ticker " + ticker + " not found");
        });
    }

    @Override
    public Collection<Stock> findByExchange_Mic(String mic) {
        return stockRepository.findByExchange_Mic(mic);
    }

    @Override
    public Collection<Stock> findByTaxGroup_Id(Short id) {
        return stockRepository.findByTaxGroup_Id(id);
    }

    @Override
    public Stock update(Stock stock, String id) {
        return stockRepository.findById(id).map(dbStock -> {
            dbStock.setTicker(stock.getTicker());
            dbStock.setName(stock.getName());
            dbStock.setExchange(stock.getExchange());
            return stockRepository.save(dbStock);
        }).orElseGet(() -> {
            stock.setId(id);
            return stockRepository.save(stock);
        });
    }

    @Override
    public void delete(String id) {
        stockRepository.deleteById(id);
    }
}
