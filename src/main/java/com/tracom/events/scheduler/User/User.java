package com.tracom.events.scheduler.User;


import com.tracom.events.scheduler.Organization.Organization;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="users_table")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(nullable = false, length=64, unique=true)
    private String user_name;
    @Column(length = 96)
    private String password;

    @ManyToOne
    @NotBlank(message = "Organization is required")
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//Override with toString()

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
