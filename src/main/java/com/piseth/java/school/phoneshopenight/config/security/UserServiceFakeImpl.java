package com.piseth.java.school.phoneshopenight.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//todo fake service
@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService {
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<AuthUser> findUserByUsername(String username) {
      List<AuthUser> users = List.of(
                new AuthUser("seyha",passwordEncoder.encode("seyha678"),RoleEnum.SALE.getAuthorities(),true,true,true,true),
                new AuthUser("socheata",passwordEncoder.encode("socheata678"),RoleEnum.ADMIN.getAuthorities(), true,true,true,true)
        );
      //todo unless until username and password has authentication
      return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
