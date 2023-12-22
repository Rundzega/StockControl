package com.stockcontrol.app.controllers;

import com.stockcontrol.app.domain.Account;
import com.stockcontrol.app.dto.RequestAccountDTO;
import com.stockcontrol.app.services.AccountService;
import com.stockcontrol.app.util.AccountModelAssembler;
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
@RequiredArgsConstructor
@RequestMapping("/account/")
public class AccountController {
    @Autowired
    private final AccountService accountService;

    @Autowired
    private final AccountModelAssembler assembler;

    @PostMapping()
    public ResponseEntity<Account> create(@Valid @RequestBody RequestAccountDTO data){
        Account account = new Account(data);
        return ResponseEntity.status(201).body(accountService.create(account));
    }

    @GetMapping()
    public CollectionModel<EntityModel<Account>> getAll() {
        List<EntityModel<Account>> accounts = accountService.findAll().stream()
                .map(assembler::toModel).toList();
        return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).getAll()).withSelfRel());
    }

    @GetMapping("/id/")
    public EntityModel<Account> getById(@PathVariable String id) {
        return EntityModel.of(accountService.findById(id));
    }
}
