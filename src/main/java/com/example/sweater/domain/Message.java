package com.example.sweater.domain;

import org.hibernate.annotations.ManyToAny;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;

    }

    //Все методы getSomething могут быть заменены на обращение к
    //полю (даже если оно не существует) с именем something. Это из groovy
    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }




    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
