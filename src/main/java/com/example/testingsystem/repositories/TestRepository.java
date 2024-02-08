package com.example.testingsystem.repositories;

import com.example.testingsystem.users.Test;
import com.example.testingsystem.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
    Test findById(long id);

    Test findByTestName(String testName);

    Test findByUser(User user);
}
