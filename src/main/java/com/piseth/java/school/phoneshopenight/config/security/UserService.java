package com.piseth.java.school.phoneshopenight.config.security;

import java.util.Optional;
//todo fake service
public interface UserService {
   Optional<AuthUser> findUserByUsername(String username);

}
