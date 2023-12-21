package com.stockcontrol.app.controllers;

import com.stockcontrol.app.domain.operation.OperationType;
import com.stockcontrol.app.dto.RequestOperationTypeDTO;
import com.stockcontrol.app.services.OperationTypeService;
import com.stockcontrol.app.util.OperationTypeModelAssembler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * OperationType
 */
@RestController
@RequestMapping("/operation-type/")
@RequiredArgsConstructor
public class OperationTypeController {

    @Autowired
    private final OperationTypeService operationTypeService;

    @Autowired
    private final OperationTypeModelAssembler assembler;

    @GetMapping()
    public CollectionModel<EntityModel<OperationType>> getAll() {
        List<EntityModel<OperationType>> operationTypes = operationTypeService.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(operationTypes, linkTo(methodOn(OperationTypeController.class).getAll()).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<OperationType> create(@Valid @RequestBody RequestOperationTypeDTO data) {
        OperationType operationType = new OperationType(data);
        return ResponseEntity.status(201).body(operationTypeService.create(operationType));
    };

    @GetMapping("{description}")
    public EntityModel<OperationType> getByDescription(@PathVariable("description") String description) {
        OperationType operationType = operationTypeService.findByDescription(description);

        return assembler.toModel(operationType);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<OperationType> delete(@PathVariable String id) {
        operationTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
