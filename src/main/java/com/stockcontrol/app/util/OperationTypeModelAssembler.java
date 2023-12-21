package com.stockcontrol.app.util;

import com.stockcontrol.app.controllers.OperationTypeController;
import com.stockcontrol.app.domain.operation.OperationType;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OperationTypeModelAssembler implements RepresentationModelAssembler<OperationType, EntityModel<OperationType>> {
    @Override
    public EntityModel<OperationType> toModel(OperationType operationType) {
        return EntityModel.of(operationType,
                linkTo(methodOn(OperationTypeController.class).getByDescription(operationType.getDescription())).withSelfRel(),
                linkTo(methodOn(OperationTypeController.class).getAll()).withRel("operation-type"));
    }
}
