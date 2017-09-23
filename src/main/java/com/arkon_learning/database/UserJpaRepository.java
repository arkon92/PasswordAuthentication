package com.arkon_learning.database;

import com.arkon_learning.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by arkon92 on 11/09/2017.
 */

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByUsername(String username);

}
