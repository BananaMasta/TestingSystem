package com.example.testingsystem.users;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class StudySubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    public StudySubject(String name) {
        this.name = name;
    }

    public StudySubject() {

    }
}
