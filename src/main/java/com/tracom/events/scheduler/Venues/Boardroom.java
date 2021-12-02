package com.tracom.events.scheduler.Venues;

import com.tracom.events.scheduler.Organization.Organization;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Boardroom")
public class Boardroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardroom_id", nullable = false, length = 45)
    private int boardroom_id;
    @Column(name = "boardroom_name", nullable = false, length = 45)
    private String boardroom_name;
    @Column(name = "capacity", nullable = false, length = 45)
    private int capacity;
    @Column(name = "tv", nullable = false, length = 45)
    private String tv;
    @Column(name = "whiteboard", nullable = false, length = 45)
    private String whiteboard;
    @Column(name = "conference_phone", nullable = false, length = 45)
    private String conference_phone;


    @ManyToOne
    @JoinColumn(name = "organization_id")
    @NotBlank(message = "Organization cannot be blank!")
    private Organization organization;


//    @OneToMany(mappedBy = "boardroom")
//    private List<Meetings> meetings = new ArrayList<>();
//
//    public List<Meetings> getMeetings() {
//        return meetings;
//    }
//
//    public void setMeetings(List<Meetings> meetings) {
//        this.meetings = meetings;
//    }
//
    public int getBoardroom_id() {
        return boardroom_id;
    }

    public void setBoardroom_id(int boardroom_id) {
        this.boardroom_id = boardroom_id;
    }

    public String getBoardroom_name() {
        return boardroom_name;
    }

    public void setBoardroom_name(String boardroom_name) {
        this.boardroom_name = boardroom_name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getWhiteboard() {
        return whiteboard;
    }

    public void setWhiteboard(String whiteboard) {
        this.whiteboard = whiteboard;
    }

    public String getConference_phone() {
        return conference_phone;
    }

    public void setConference_phone(String conference_phone) {
        this.conference_phone = conference_phone;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Boardroom{" +
                "boardroom_id=" + boardroom_id +
                ", boardroom_name='" + boardroom_name + '\'' +
                ", capacity=" + capacity +
                ", tv='" + tv + '\'' +
                ", whiteboard='" + whiteboard + '\'' +
                ", conference_phone='" + conference_phone + '\'' +
                '}';
    }
}
