package com.bib.dao.book;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    public String getName() {
        return name;
    }

    public Integer getId() {
        return Id;
    }

    public Integer getAutor_id() {
        return autor_id;
    }

    public Autor getAutorMapper() {
        return autorMapper;
    }

    @Size(min = 2, max = 30)
    protected String name;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "autor_id")
    public Integer autor_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", insertable = false, updatable = false)
    private Autor autorMapper;

    public Book(Integer autor_id, @Size(min = 2, max = 30) String name) {
        this.autor_id = autor_id;
        this.name = name;
//        this.date = new java.sql.Date(date.getTime());
    }
}
