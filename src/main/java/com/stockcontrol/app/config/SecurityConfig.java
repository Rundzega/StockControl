package com.stockcontrol.app.config;

import com.stockcontrol.app.services.implementation.CustomOAuth2UserService;
import com.stockcontrol.app.services.implementation.CustomOidcUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final CustomOAuth2UserService oAuth2UserService;

    @Autowired
    private final CustomOidcUserService oidcUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> {
                auth.requestMatchers("/").permitAll();
                auth.anyRequest().authenticated();
            })
            .oauth2Login(oauthLogin -> oauthLogin
                    .userInfoEndpoint(userInfo -> {
                        userInfo.userService(oAuth2UserService);
                        userInfo.oidcUserService(oidcUserService);
                    })
            )
            .build();
    }
}
