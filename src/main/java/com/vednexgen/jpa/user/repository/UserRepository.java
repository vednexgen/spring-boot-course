package com.vednexgen.jpa.user.repository;

import com.vednexgen.jpa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}