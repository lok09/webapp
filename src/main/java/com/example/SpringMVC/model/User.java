package com.example.SpringMVC.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER_INFO")
public class User implements Serializable {

    @Id
    @Column(name="username",length=20, nullable=false, unique = true, updatable = false)
    private String username;
    @Column(name="password", length=25, nullable=false)
    private String password;
    @Column(name="user_role", length=20, nullable=false)
    private String role;
    @Column(name="fullName", length=50, nullable=false)
    private String fullName;
    @Column(name="phone_number", length=20, nullable=false)
    private int phoneNumber;
    @Column(name="address", length=250, nullable=false)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<LectureComment> lectureComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PollResult> pollResults;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<LectureComment> getComments() {
        return lectureComments;
    }

    public void setComments(List<LectureComment> lectureComments) {
        this.lectureComments = lectureComments;
    }

    public List<PollResult> getPollResults() {
        return pollResults;
    }

    public void setPollResults(List<PollResult> pollResults) {
        this.pollResults = pollResults;
    }

}
