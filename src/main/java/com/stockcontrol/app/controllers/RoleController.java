package com.stockcontrol.app.controllers;

import com.stockcontrol.app.domain.account.Role;
import com.stockcontrol.app.dto.RequestRoleDTO;
import com.stockcontrol.app.services.RoleService;
import com.stockcontrol.app.util.RoleModelAssembler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/role/")
@RequiredArgsConstructor
public class RoleController {

    @Autowired
    private final RoleService roleService;

    @Autowired
    private final RoleModelAssembler assembler;

    @PostMapping()
    public ResponseEntity<Role> create(@Valid @RequestBody RequestRoleDTO data) {
        Role role = new Role(data);
        return ResponseEntity.status(201).body(roleService.create(role));
    }

    @GetMapping("/{id}/")
    public EntityModel<Role> getById(@PathVariable String id) {
        return assembler.toModel(roleService.findById(id));
    }

    @GetMapping()
    public CollectionModel<EntityModel<Role>> getAll() {
        List<EntityModel<Role>> roles = roleService.findAll().stream()
                .map(assembler::toModel).toList();

        return CollectionModel.of(roles, linkTo(methodOn(RoleController.class).getAll()).withSelfRel());
    }
}
