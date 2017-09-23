package com.arkon_learning.rest;

import com.arkon_learning.configuration.ApplicationConfiguration;
import com.arkon_learning.database.UserJpaRepository;
import com.arkon_learning.database.entity.UserEntity;
import com.arkon_learning.rest.response.AuthenticationResponse;
import com.arkon_learning.rest.response.RegistrationResponse;
import com.arkon_learning.user.UserAuthenticationInfo;
import com.arkon_learning.user.UserRegistrationInfo;
import com.arkon_learning.utils.ResourceFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by arkon92 on 10/09/2017.
 */


@Controller
@EnableAutoConfiguration
@EnableJpaRepositories(value = "com.arkon_learning.database")
@ComponentScan(value = {"com.arkon_learning.configuration", "com.arkon_learning.security"})
@EntityScan(value = "com.arkon_learning.database.entity")
public class AuthenticationController {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<Iterable<UserEntity>> findByRepo(@RequestHeader(value = "Token") String userAgent) throws IOException {
        return new ResponseEntity<>(userJpaRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping("/")
    @ResponseBody
    public String indexPage() {
        logger.debug("Access to index.html");
        ResourceFileReader indexReader = new ResourceFileReader(applicationConfiguration.getIndexFile());
        return indexReader.getFileContentAsString();
    }

    @PostMapping("/registerUser")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody UserRegistrationInfo userRegistrationInfo) {
        logger.debug("Received {}", userRegistrationInfo);

        String hashedPassword = passwordEncoder.encode(userRegistrationInfo.getPassword());
        UserEntity model = new UserEntity(new SecureRandom().nextLong(), userRegistrationInfo.getEmail(),
                userRegistrationInfo.getUsername(), hashedPassword);
        userJpaRepository.save(model);

        RegistrationResponse registrationResponse = new RegistrationResponse(userRegistrationInfo.getUsername());

        return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
    }

    @PostMapping("/authenticateUser")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody UserAuthenticationInfo userAuthenticationInfo) {
        logger.debug("Received {}", userAuthenticationInfo);

        List<UserEntity> userEntity = null;
        userEntity = userJpaRepository.findByUsername(userAuthenticationInfo.getUsername());
        if (userEntity.size() > 0) {
            for (UserEntity entity : userEntity) {
                if (passwordEncoder.matches(userAuthenticationInfo.getPassword(), entity.getPassword())) {
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AuthenticationController.class, args);
    }

}
