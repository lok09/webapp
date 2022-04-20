package com.example.SpringMVC.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Poll implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pollID;
    @Column(name="question", length = 200)
    private String question;
    @Column(name="option_a", length=50)
    private String optionA;
    @Column(name="option_b", length=50)
    private String optionB;
    @Column(name="option_c", length=50)
    private String optionC;
    @Column(name="option_d", length=50)
    private String optionD;
    @Column(name="answer", length=1)
    private String answer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollResult> pollResults;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollComment> pollComments;

    public List<PollComment> getPollComments() {
        return pollComments;
    }

    public void setPollComments(List<PollComment> pollComments) {
        this.pollComments = pollComments;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPollID() {
        return pollID;
    }

    public void setPollID(Long pollID) {
        this.pollID = pollID;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public List<PollResult> getPollResults() {
        return pollResults;
    }

    public void setPollResults(List<PollResult> pollResults) {
        this.pollResults = pollResults;
    }
}
