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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Size(min = 2, max = 30)
    protected String name;

    private Long email;

    private String password;

    @Column(name = "enabled")
    private Integer enabled;

    public User(@Size(min = 2, max = 30) String name, Long email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
