package com.example.testingsystem.repositories;

import com.example.testingsystem.users.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {
    Groups findByGroupName(String groupName);

    Groups findById(long id);
}
