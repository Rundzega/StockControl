package com.stockcontrol.app.util;

import com.stockcontrol.app.controllers.AccountController;
import com.stockcontrol.app.domain.Account;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {
    @Override
    public EntityModel<Account> toModel(Account account) {
        return EntityModel.of(account,
                linkTo(methodOn(AccountController.class).getById(account.getId())).withSelfRel(),
                linkTo(methodOn(AccountController.class).getAll()).withRel("account"));
    }
}
