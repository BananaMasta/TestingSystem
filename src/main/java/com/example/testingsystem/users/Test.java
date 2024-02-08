package com.example.testingsystem.users;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String testName;
    @OneToMany
    private List<Question> questionList = new ArrayList<>();
    private double studentResult;
    @ManyToOne
    private StudySubject studySubjectList;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
