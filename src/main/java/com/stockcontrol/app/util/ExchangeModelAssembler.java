package com.stockcontrol.app.util;

import com.stockcontrol.app.controllers.ExchangeController;
import com.stockcontrol.app.controllers.StockController;
import com.stockcontrol.app.domain.stock.Exchange;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ExchangeModelAssembler implements RepresentationModelAssembler<Exchange, EntityModel<Exchange>> {
    @Override
    public EntityModel<Exchange> toModel(Exchange exchange) {
        return EntityModel.of(exchange,
            linkTo(methodOn(ExchangeController.class).getByMic(exchange.getMic())).withSelfRel(),
            linkTo(methodOn(ExchangeController.class).getAll()).withRel("exchange")
        );
    }
}
