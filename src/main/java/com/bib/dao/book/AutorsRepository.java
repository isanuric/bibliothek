package com.bib.dao.book;


import java.util.Collection;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorsRepository extends JpaRepository<Autor, Integer> {

    //    @Query(value = "SELECT * FROM autor a INNER JOIN book where a.id=book.autor_id AND a.name = 'Martin'", nativeQuery = true)
    @Query(value = "SELECT * FROM autor a INNER JOIN book WHERE a.id=book.autor_id AND a.name = :name", nativeQuery = true)
    Set<Autor> getAuthorAndBooks(@Param("name") String name);

    @Query(value = "SELECT b.name FROM autor a INNER JOIN book b WHERE a.id=b.autor_id AND a.name = 'Martin'", nativeQuery = true)
    Set<Object> getOnlyBooks(@Param("name") String name);

    @Query(value = "SELECT * FROM autor", nativeQuery = true)
    Collection<Autor> getAllBooks();

    @Query(value = "SELECT * FROM autor WHERE autor.name = :name", nativeQuery = true)
    Collection<Autor> findAutorByFirstname(@Param("name") String name);


}
