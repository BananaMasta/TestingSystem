package com.example.testingsystem.repositories;

import com.example.testingsystem.users.StudySubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<StudySubject, Long> {
    StudySubject findByName(String name);
}
