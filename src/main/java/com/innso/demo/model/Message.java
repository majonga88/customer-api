package com.innso.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "message")
@Data
@AllArgsConstructor
public class Message {

    public Message() {
        this.id = null;
        this.channel = Channel.MAIL;
    }

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull @Column(name = "date")
    private Date date;
    @NotNull @Column(name = "author_name")
    private String authorName;
    @NotNull @Column(name = "content")
    private String content;
    @NotNull @Column(name = "channel")
    @Enumerated(EnumType.STRING)
    private Channel channel;

    enum Channel {

        MAIL,
        SMS,
        FACEBOOK,
        TWITTER
    }
}
