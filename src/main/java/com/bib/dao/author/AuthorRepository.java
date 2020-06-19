package com.bib.dao.author;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query(value = "SELECT * FROM author a INNER JOIN book WHERE a.id=book.author_id AND a.surname = :surname", nativeQuery = true)
    Set<Author> getAuthorAndBooks(@Param("surname") String surname);

    @Query(value = "SELECT b.name FROM author a INNER JOIN book b WHERE a.id=b.author_id AND a.surname = :surname", nativeQuery = true)
    Set<List> getBooksBySurname(@Param("surname") String surname);

    @Query(value = "SELECT a.surname,b.name FROM author a INNER JOIN book b WHERE a.id=b.author_id AND a.name = :name", nativeQuery = true)
    Set<List> getAuthorAndBooksDirect(@Param("name") String name);

    @Query(value = "SELECT * FROM author", nativeQuery = true)
    Collection<Author> getAllBooks();

    @Query("SELECT a.name FROM Author a")
    Set<Object> getAllAuthors();

    @Query("SELECT a.name, a.surname FROM Author a")
    Set<List> getAllAuthorsFullName();

    void deleteBySurname(@Param("surname") String username);
}

