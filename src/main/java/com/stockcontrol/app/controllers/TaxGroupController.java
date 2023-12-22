package com.stockcontrol.app.controllers;


import com.stockcontrol.app.domain.stock.Stock;
import com.stockcontrol.app.domain.stock.TaxGroup;
import com.stockcontrol.app.dto.RequestTaxGroupDTO;
import com.stockcontrol.app.services.TaxGroupService;
import com.stockcontrol.app.util.StockModelAssembler;
import com.stockcontrol.app.util.TaxGroupModelAssembler;
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

@RestController
@RequestMapping("/tax-group/")
@RequiredArgsConstructor
public class TaxGroupController {

    @Autowired
    private final TaxGroupService taxGroupService;

    @Autowired
    private final TaxGroupModelAssembler assembler;

    @PostMapping()
    public ResponseEntity<TaxGroup> create(@Valid @RequestBody RequestTaxGroupDTO data) {
        TaxGroup taxGroup = new TaxGroup(data);
        return ResponseEntity.status(201).body(taxGroupService.create(taxGroup));
    }

    @GetMapping()
    public CollectionModel<EntityModel<TaxGroup>> getAll() {
        List<EntityModel<TaxGroup>> taxGroups = taxGroupService.findAll().stream()
                .map(assembler::toModel).toList();

        return CollectionModel.of(taxGroups, linkTo(methodOn(TaxGroupController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}/")
    public EntityModel<TaxGroup> getById(@PathVariable Short id) {
        return assembler.toModel(taxGroupService.findById(id));
    }
}
