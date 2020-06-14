package com.bib.dao.user;


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

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    private String email;
    private String password;
    private int enabled;

    public Members(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = 1;
    }
}
