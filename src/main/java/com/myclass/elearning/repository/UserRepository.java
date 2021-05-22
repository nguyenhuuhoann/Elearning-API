package com.myclass.elearning.repository;

import com.myclass.elearning.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsernameOrEmail(String username, String email);
}
