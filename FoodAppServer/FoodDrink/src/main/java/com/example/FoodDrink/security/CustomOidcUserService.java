package com.example.FoodDrink.security;


import com.example.FoodDrink.entity.Role;
import com.example.FoodDrink.entity.User;
import com.example.FoodDrink.exceptions.NotFoundException;
import com.example.FoodDrink.repository.RoleRepository;
import com.example.FoodDrink.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    private final OidcUserService oidcUserService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;



    @Override
    @Transactional
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("Loading OIDC user for registration ID: {}", registrationId);


        OidcUser oidcUser = oidcUserService.loadUser(userRequest);

        String name = oidcUser.getAttribute("name");
        String email = oidcUser.getAttribute("email");
        log.info("User name: {}, email: {}", name, email);

        User user = userRepository.findByEmail(email)
                .orElse(null);
        if (user == null) {
            // roles list
            Role customerRole = roleRepository.findByName("CUSTOMER")
                    .orElseThrow(() -> new NotFoundException("Role CUSTOMER not found"));
            List<Role> roles = new ArrayList<>();
            roles.add(customerRole);
            // create new user
            user = User.builder()
                    .name(name)
                    .email(email)
                    .password("")
                    .profileUrl(oidcUser.getAttribute("picture"))
                    .roles(roles
                    ) // Mặc định gán vai trò CUSTOMER
                    .build();
            userRepository.save(user);
            log.info("Created new user: {}", user);
        } else {
            log.info("User already exists: {}", user);
        }

        return oidcUser;
    }

}