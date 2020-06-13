package com.bib.dao.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "members")
public class Members {


    @Id
//    @Size(min = 2, max = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username")
    protected String username;

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
        this.enabled = 1;
    }
}
