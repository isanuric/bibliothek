package com.bib.dao.book;


import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 */

@Repository
public interface  BookRepository extends CrudRepository<Book, Integer> {

    @Query(value = "SELECT * FROM book b ORDER BY b.id", nativeQuery = true)
    Collection<Book> findAllExistBooks();



}
