package com.stockcontrol.app.services;

import com.stockcontrol.app.domain.Account;

import java.util.Collection;

public interface AccountService {
    public Account create(Account account);

    public Collection<Account> findAll();

    public Account findById(String id);

    public Account findByEmail(String email);

    public Account update(Account account, String id);

    public void delete(String id);
}
