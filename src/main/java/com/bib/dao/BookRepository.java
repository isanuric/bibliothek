package com.bib.dao;


import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 */

public interface  BookRepository extends CrudRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE b.status = 1")
    Collection<Book> findAllExistBooks();

}
