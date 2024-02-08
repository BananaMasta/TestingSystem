package com.example.testingsystem.users;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ADMINLEARNINGPROCESS,
    SYSTEMADMIN,
    TEACHER,
    STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }
}

