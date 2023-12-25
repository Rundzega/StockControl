package com.stockcontrol.app.services.implementation;

import com.stockcontrol.app.domain.account.Account;
import com.stockcontrol.app.domain.account.Role;
import com.stockcontrol.app.repositories.AccountRepository;
import com.stockcontrol.app.services.AccountService;
import com.stockcontrol.app.services.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final RoleService roleService;

    @Override
    public Account create(Account account) {
        if (accountRepository.findByProviderAndProviderId(account.getProvider(), account.getProviderId()).isPresent()) {
            throw new DataIntegrityViolationException("Account already registered.");
        }
        Role role = roleService.findById("user");
        account.setRole(role);
        return accountRepository.save(account);
    }

    @Override
    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(String id) {
        return accountRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Account not found.");
        });
    }

    @Override
    public Account findByProviderAndProviderId(String provider, String providerId) {
        return accountRepository.findByProviderAndProviderId(provider, providerId).orElseThrow(() -> {
            throw new EntityNotFoundException("Account not found.");
        });
    }

    @Override
    public Account update(Account account, String id) {
        return accountRepository.findById(id).map(dbAccount -> {
            dbAccount.setRole(account.getRole());
            dbAccount.setEmail(account.getEmail());
            return accountRepository.save(dbAccount);
        }).orElseGet(() -> {
            account.setId(id);
            return accountRepository.save(account);
        });
    }

    @Override
    public void delete(String id) {
        accountRepository.deleteById(id);
    }
}
