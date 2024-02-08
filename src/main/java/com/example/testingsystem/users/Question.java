package com.example.testingsystem.users;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String questName;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> answerVariants;
    @ManyToOne
    private StudySubject studySubject;
    public String answers;
    private QuestionDifficult questionDifficult;

    public Question() {
    }

    public Question(String questName, List<String> answerVariants, StudySubject studySubject, String answers, QuestionDifficult questionDifficult) {
        this.questName = questName;
        this.answerVariants = answerVariants;
        this.studySubject = studySubject;
        this.answers = answers;
        this.questionDifficult = questionDifficult;
    }
}
