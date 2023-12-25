package com.stockcontrol.app.services.implementation;

import com.stockcontrol.app.domain.account.Account;
import com.stockcontrol.app.services.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private final AccountService accountService;

    @Override
    @SneakyThrows
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
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
        return oAuth2User;
    }
}
