package com.bib.dao.book;


import java.sql.Date;
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
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Size(min = 2, max = 30)
    protected String name;

    private String autor;

    private Long isbn;

    @Column(name="status")
    private Integer status;

    private Date timestamp;

    public Book(@Size(min = 2, max = 30) String name, String autor, Long isbn) {
        this.name = name;
        this.autor = autor;
        this.isbn = isbn;
        this.status = 1;
    }

}
