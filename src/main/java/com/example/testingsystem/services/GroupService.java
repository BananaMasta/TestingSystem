package com.example.testingsystem.services;

import com.example.testingsystem.users.Groups;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    public Groups groupCreate(String groupName) {
        Groups group = new Groups();
        group.setGroupName(groupName);
        return group;
    }
}
