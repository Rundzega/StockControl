package com.stockcontrol.app.util;

import com.stockcontrol.app.controllers.StockController;
import com.stockcontrol.app.domain.stock.Stock;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StockModelAssembler implements RepresentationModelAssembler<Stock, EntityModel<Stock>> {
    @Override
    public EntityModel<Stock> toModel(Stock stock) {
        return EntityModel.of(stock,
                linkTo(methodOn(StockController.class).getByTickerAndExchangeMic(stock.getTicker(), stock.getExchange().getMic())).withSelfRel(),
                linkTo(methodOn(StockController.class).getByExchange(stock.getExchange().getMic())).withRel("exchange/" + stock.getExchange().getMic() + "/stock"));
    }
}
