package org.innso.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "message")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
