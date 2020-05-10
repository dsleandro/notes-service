package com.leandro.notesservice.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "notes")
public class Note {

   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="title")
    private String title;
    
    @Column(name="content")
    private String content;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @JsonIgnoreProperties({"notes", "password"})
    @ManyToOne(optional = false)
    @JoinColumn(name = "user")
    private User user;

    protected Note() {
    }

    public Note(String title, String content, User user, Date date) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "Note [ id=" + id +", content=" + content + ", date=" + date + ", title=" + title + ", user=" + user.getUsername() + "]";
    }

}