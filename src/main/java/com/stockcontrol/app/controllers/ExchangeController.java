package com.stockcontrol.app.controllers;

import com.stockcontrol.app.domain.stock.Exchange;
import com.stockcontrol.app.dto.RequestExchangeDTO;
import com.stockcontrol.app.services.ExchangeService;
import com.stockcontrol.app.util.ExchangeModelAssembler;
import jakarta.validation.Valid;
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
@RequestMapping("/exchange/")
@RequiredArgsConstructor
public class ExchangeController {

    @Autowired
    private final ExchangeService exchangeService;

    @Autowired
    private final ExchangeModelAssembler assembler;

    @PostMapping()
    public ResponseEntity<Exchange> create(@Valid @RequestBody RequestExchangeDTO data) {
        Exchange exchange = new Exchange(data);
        return ResponseEntity.status(201).body(exchangeService.create(exchange));
    };

    @GetMapping()
    public CollectionModel<EntityModel<Exchange>> getAll() {
        List<EntityModel<Exchange>> exchanges = exchangeService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(exchanges, linkTo(methodOn(ExchangeController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{mic}/")
    public EntityModel<Exchange> getByMic(@PathVariable String mic) {
        Exchange exchange = exchangeService.findByMic(mic);

        return assembler.toModel(exchange);
    }
}
