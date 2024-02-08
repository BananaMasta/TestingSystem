package com.example.testingsystem.repositories;

import com.example.testingsystem.users.StudentAnswers;
import com.example.testingsystem.users.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAnsweresRepository extends JpaRepository<StudentAnswers, Long> {
    StudentAnswers findById(long id);

    List<StudentAnswers> findByTest(Test test);
}
