package com.stockcontrol.app.util;

import com.stockcontrol.app.controllers.TaxGroupController;
import com.stockcontrol.app.domain.stock.TaxGroup;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaxGroupModelAssembler implements RepresentationModelAssembler<TaxGroup, EntityModel<TaxGroup>> {
    @Override
    public EntityModel<TaxGroup> toModel(TaxGroup taxGroup) {
        return EntityModel.of(taxGroup,
                linkTo(methodOn(TaxGroupController.class).getById(taxGroup.getId())).withSelfRel(),
                linkTo(methodOn(TaxGroupController.class).getAll()).withRel("tax-group"));
    }
}
