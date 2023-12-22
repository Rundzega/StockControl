package com.stockcontrol.app.repositories;

import com.stockcontrol.app.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, String> {
    Optional<Stock> findByTickerAndExchange_Mic(String ticker, String mic);

    Collection<Stock> findByExchange_Mic(String mic);

    Collection<Stock> findByTaxGroup_Id(Short id);
}
