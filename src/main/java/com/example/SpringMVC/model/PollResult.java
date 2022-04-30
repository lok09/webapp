package com.example.SpringMVC.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class PollResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="poll_result_id")
    private Long pollResultID;
    @Column(name="user_option", length=1)
    private String option;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="submit_date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPollResultID() {
        return pollResultID;
    }

    public void setPollResultID(Long pollResultID) {
        this.pollResultID = pollResultID;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

}
