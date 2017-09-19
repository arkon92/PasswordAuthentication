package com.arkon_learning.database;

import com.arkon_learning.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by arkon92 on 11/09/2017.
 */

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
