package com.example.testingsystem.users;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String groupName;
    @OneToMany
    private List<User> studentList;


}
