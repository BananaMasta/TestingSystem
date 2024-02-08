package com.example.testingsystem.users;

import lombok.Data;


import javax.persistence.*;

import java.util.List;
@Entity
@Data
public class StudentAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    private Test test;
    @OneToOne
    private User user;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> resultList;
}
