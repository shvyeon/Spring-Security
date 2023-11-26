package com.cos.security_1.repository;

import com.cos.security_1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // find by 규칙 -> Username 문법
    // select * from user where username = 1?
    public User findByUsername(String username); // Jpa Query Methods

    // select * from user where email = ?
//    public User findByEmail();
}
