package com.artur.engineer.entities;

import com.artur.engineer.engine.views.UserView;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_SUPER_USER = "ROLE_SUPER_USER";
    public static final String ROLE_TEACHER = "ROLE_TEACHER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String AVAILABLE_ROLES[] = {
            ROLE_ADMIN,
            ROLE_TEACHER,
            ROLE_SUPER_USER,
            ROLE_USER
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({UserView.Normal.class})
    private Long id;

    @JsonView({UserView.Normal.class})
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
