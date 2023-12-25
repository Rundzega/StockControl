package com.stockcontrol.app.services.implementation;

import com.stockcontrol.app.domain.account.Account;
import com.stockcontrol.app.services.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {
    @Autowired
    private final AccountService accountService;
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oidcUser.getAttribute("sub");
        String email = oidcUser.getAttribute("email");
        try {
            Account dbAccount = accountService.findByProviderAndProviderId(provider, providerId);
            dbAccount.setEmail(email);
            accountService.update(dbAccount, dbAccount.getId());
        } catch (EntityNotFoundException ex) {
            Account newAcc = new Account();
            newAcc.setProvider(provider);
            newAcc.setProviderId(providerId);
            newAcc.setEmail(email);
            accountService.create(newAcc);
        }
        return oidcUser;
    };
}
