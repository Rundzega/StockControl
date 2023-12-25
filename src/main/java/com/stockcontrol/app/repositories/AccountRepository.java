package com.stockcontrol.app.repositories;

import com.stockcontrol.app.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByProviderAndProviderId(String provider, String providerId);
}
