package com.bib.dao.book;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorsRepository extends JpaRepository<Autor, Integer> {

    //    @Query(value = "SELECT * FROM autor a INNER JOIN book where a.id=book.autor_id AND a.name = 'Martin'", nativeQuery = true)
    @Query(value = "SELECT * FROM autor a INNER JOIN book WHERE a.id=book.autor_id AND a.surname = :surname", nativeQuery = true)
    Set<Autor> getAuthorAndBooks(@Param("surname") String surname);

    @Query(value = "SELECT b.name FROM autor a INNER JOIN book b WHERE a.id=b.autor_id AND a.surname = :surname", nativeQuery = true)
    Set<List> getBooksBySurname(@Param("surname") String surname);

    @Query(value = "SELECT a.surname,b.name FROM autor a INNER JOIN book b WHERE a.id=b.autor_id AND a.name = :name", nativeQuery = true)
    Set<List> getAuthorAndBooksDirect(@Param("name") String name);

    @Query(value = "SELECT * FROM autor", nativeQuery = true)
    Collection<Autor> getAllBooks();

    @Query(value = "SELECT * FROM autor WHERE autor.name = :name", nativeQuery = true)
    Collection<Autor> findAutorByFirstname(@Param("name") String name);

    @Query("SELECT a.name FROM Autor a")
    Set<Object> getAllAuthors();

    @Query("SELECT a.name, a.surname FROM Autor a")
    Set<List> getAllAuthorsFullName();
}
