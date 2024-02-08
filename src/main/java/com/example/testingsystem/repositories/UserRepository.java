package com.example.testingsystem.repositories;

import com.example.testingsystem.users.Roles;
import com.example.testingsystem.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String name);

    User findById(long id);

    User findByName(String username);

    List<User> findByUserRolesContaining(Roles userRole);
}

