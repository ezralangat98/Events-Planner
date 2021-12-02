package com.tracom.events.scheduler.Organization;


import com.tracom.events.scheduler.Organization.Organization;
import com.tracom.events.scheduler.User.User;
import com.tracom.events.scheduler.Venues.Boardroom;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id", nullable = false, length = 45)
    private int organization_id;
    @Column(name = "organization_name",nullable = false, length = 45, unique = true)
    private String organization_name;

//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "organization_boardrooms",
//            joinColumns = @JoinColumn(name = "organization_id"),
//            inverseJoinColumns = @JoinColumn(name = "boardroom_id")
//    )
//    private Set<Boardroom> boardrooms = new HashSet<>();

    @OneToMany(mappedBy = "organization")
    List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "organization")
    List<Boardroom> boardrooms = new ArrayList<>();


    public int getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }



    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Boardroom> getBoardrooms() {
        return boardrooms;
    }

    public void setBoardrooms(List<Boardroom> boardrooms) {
        this.boardrooms = boardrooms;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organization_id=" + organization_id +
                ", organization_name='" + organization_name + '\'' +
                '}';
    }
}
