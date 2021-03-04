package org.innso.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "client_folder")
public class ClientFolder {

    public ClientFolder() {
        this.id = null;
    }

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull @Column(name = "client_name")
    private String clientName;
    @NotNull @Column(name = "opened_date")
    private Date openedDate;
    @NotNull @Column(name = "reference")
    private String reference;
    @OneToMany
    private List<Message> messages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(Date openedDate) {
        this.openedDate = openedDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
