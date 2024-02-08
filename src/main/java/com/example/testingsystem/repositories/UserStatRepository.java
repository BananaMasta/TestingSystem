package com.example.testingsystem.repositories;

import com.example.testingsystem.users.Test;
import com.example.testingsystem.users.User;
import com.example.testingsystem.users.UserStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserStatRepository extends JpaRepository<UserStatistic, Long> {
    List<UserStatistic> findByUser(User user);

    List<UserStatistic> findByTest(Test test);
}
