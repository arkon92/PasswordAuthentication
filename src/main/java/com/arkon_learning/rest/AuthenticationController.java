package com.arkon_learning.rest;

import com.arkon_learning.configuration.ApplicationConfiguration;
import com.arkon_learning.database.UserJpaRepository;
import com.arkon_learning.database.entity.UserEntity;
import com.arkon_learning.user.UserInfo;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.SecureRandom;

/**
 * Created by arkon92 on 10/09/2017.
 */


@Controller
@EnableAutoConfiguration
@EnableJpaRepositories(value = "com.arkon_learning.database")
@ComponentScan(value = {"com.arkon_learning.configuration", "com.arkon_learning.database"})
@EntityScan(value = "com.arkon_learning")
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<Iterable<UserEntity>> findByRepo() throws IOException {
        return new ResponseEntity<Iterable<UserEntity>>(userJpaRepository.findAll(), HttpStatus.OK);
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
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserInfo userInfo) {
        logger.debug("Received {}", userInfo);
        UserEntity model = new UserEntity(new SecureRandom().nextLong(), userInfo.getEmail(),
                userInfo.getUsername(), userInfo.getPassword());
        UserEntity result = userJpaRepository.save(model);

        return new ResponseEntity<UserEntity>(result, HttpStatus.OK);
    }

   /* @GetMapping("/repo/{value}")
    public ResponseEntity saveByRepo(@PathVariable String value) {
        UserInfo model = new UserInfo(new SecureRandom().nextLong(), value, value, value);
        modelJpaRepository.save(model);
        return new ResponseEntity(HttpStatus.OK);
    }*/

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AuthenticationController.class, args);
    }

}
