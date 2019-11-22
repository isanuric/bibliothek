package com.bib.dao.user;


import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 */

@Repository
public interface MembersRepository extends CrudRepository<Members, Integer> {

    @Query("SELECT m FROM Members m WHERE m.enabled = 1")
    Collection<Members> findAllExistUsers();

}
