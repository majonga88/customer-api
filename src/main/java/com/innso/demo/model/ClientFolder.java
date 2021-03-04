package com.innso.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "client_folder")
@Data
@AllArgsConstructor
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

}
