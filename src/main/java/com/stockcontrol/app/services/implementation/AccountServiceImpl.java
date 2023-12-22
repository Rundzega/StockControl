package com.stockcontrol.app.services.implementation;

import com.stockcontrol.app.domain.Account;
import com.stockcontrol.app.repositories.AccountRepository;
import com.stockcontrol.app.services.AccountService;
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

    @Override
    public Account create(Account account) {
        if (accountRepository.findByEmail(account.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("email already registered.");
        }
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
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(() -> {
            throw new EntityNotFoundException("Account not found.");
        });
    }

    @Override
    public Account update(Account account, String id) {
        return accountRepository.findById(id).map(dbAccount -> {
            dbAccount.setFirstName(account.getFirstName());
            dbAccount.setLastName(account.getLastName());
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
