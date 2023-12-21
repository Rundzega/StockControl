package com.stockcontrol.app.controllers;

import com.stockcontrol.app.domain.stock.Stock;
import com.stockcontrol.app.dto.RequestStockDTO;
import com.stockcontrol.app.services.StockService;
import com.stockcontrol.app.util.StockModelAssembler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/exchange/{exchangeMic}/stock/")
@RequiredArgsConstructor
public class StockController {

    @Autowired
    private final StockService stockService;

    @Autowired
    private final StockModelAssembler assembler;

//    @GetMapping()
//    public CollectionModel<EntityModel<Stock>> getAll() {
//        List<EntityModel<Stock>> stocks = stockService.findAll().stream()
//                .map(assembler::toModel)
//                .collect(Collectors.toList());
//
//        return CollectionModel.of(stocks, linkTo(methodOn(StockController.class).getAll()).withSelfRel());
//    }

    @PostMapping()
    public ResponseEntity<Stock> create(@Valid @RequestBody RequestStockDTO data, @PathVariable("exchangeMic") @Size(min = 4, max = 4) String exchangeMic) {
        Stock stock = new Stock(data, exchangeMic);
        return ResponseEntity.status(201).body(stockService.create(stock));
    }

    @GetMapping("/{ticker}/")
    public EntityModel<Stock> getByTickerAndExchangeMic(@PathVariable("exchangeMic") String mic, @PathVariable("ticker") String ticker) {
        return assembler.toModel(stockService.findByTickerAndExchange_Mic(ticker, mic));
    }

    @GetMapping()
    public CollectionModel<EntityModel<Stock>> getByExchange(@PathVariable String exchangeMic) {
        List<EntityModel<Stock>> stocks = stockService.findByExchange_Mic(exchangeMic).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(stocks, linkTo(methodOn(StockController.class).getByExchange(exchangeMic)).withSelfRel());
    }
}
