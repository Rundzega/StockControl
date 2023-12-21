package com.stockcontrol.app.services;

import com.stockcontrol.app.domain.stock.Stock;

import java.util.Collection;

public interface StockService {
    Stock create(Stock stock);

    Collection<Stock> findAll();

    Stock findById(String id);

    Stock findByTickerAndExchange_Mic(String ticker, String mic);

    Collection<Stock> findByExchange_Mic(String mic);

    Stock update(Stock stock, String id);

    void delete(String id);
}
