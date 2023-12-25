package com.stockcontrol.app.services;

import com.stockcontrol.app.domain.account.Account;

import java.util.Collection;

public interface AccountService {
    public Account create(Account account);

    public Collection<Account> findAll();

    public Account findById(String id);

    public Account findByProviderAndProviderId(String Provider, String ProviderId);

    public Account update(Account account, String id);

    public void delete(String id);
}
