package com.bib.dao.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "bib_users")
public class User {

    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Size(min = 2, max = 30)
    @Column(name = "username")
    protected String username;

    private String email;

    private String password;

    @Column(name = "enabled")
    private Integer enabled;


    public User(String username, String password, String email) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.enabled = 1;
    }
}
