package com.example.calendar.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String text;
    private String yers;
    private String mounth;
    private String days ;
    private String clock;
    private String minuts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Message(String text, String yers, String mounth, String days, String clock, String minuts, User author) {
        this.text = text;
        this.yers = yers;
        this.mounth = mounth;
        this.days = days;
        this.clock = clock;
        this.minuts = minuts;
        this.author = author;
    }
}
