package com.example.testingsystem.repositories;

import com.example.testingsystem.users.Question;
import com.example.testingsystem.users.StudySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findById(long id);

    Question findByQuestName(String name);

    List<Question> findByStudySubject(StudySubject studySubject);
}
