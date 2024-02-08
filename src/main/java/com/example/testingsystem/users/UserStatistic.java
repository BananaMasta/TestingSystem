package com.example.testingsystem.users;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
    private String testStatus;
    private double userTestScore;

    public UserStatistic(User user, Test test, String testStatus, double userTestScore) {
        this.user = user;
        this.test = test;
        this.testStatus = testStatus;
        this.userTestScore = userTestScore;
    }

    public UserStatistic() {

    }
}
