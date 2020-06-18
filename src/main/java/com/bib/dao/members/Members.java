package com.bib.dao.members;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Members {

    @Id
    private String username;

    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    private String email;
    private int enabled;

    public Members(String username, String password, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.enabled = 1;
    }


    public Members(String username, String password, String name, String surname, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.enabled = 1;
    }
}
