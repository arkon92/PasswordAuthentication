package com.arkon_learning.security;

import com.arkon_learning.configuration.ApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by arkon92 on 23/09/2017.
 */
@Component
public class ApplicationPasswordEncoder {

    @Bean
    public PasswordEncoder passwordEncoder(ApplicationConfiguration configuration) {
        return new Pbkdf2PasswordEncoder(configuration.getPasswordStorageSecret(), 100000, 64);
    }

}
