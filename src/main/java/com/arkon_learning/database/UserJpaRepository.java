package com.arkon_learning.database;

import com.arkon_learning.database.entity.UserEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by arkon92 on 11/09/2017.
 */

@Component
@ComponentScan(value = "com.arkon_learning.configuration.entity")
@EntityScan(value = "com.arkon_learning.configuration.entity")
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
