package com.bib.dao.user;


import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 */

@Repository
public interface MembersRepository extends CrudRepository<Members, Integer> {

    @Query("SELECT m FROM Members m WHERE m.enabled = 1")
    Collection<Members> findAllExistUsers();

    @Query(value = "SELECT * FROM Members m WHERE m.username = :username", nativeQuery = true)
    Members findByUsername(@Param("username") String username);

    @Modifying
//    @Query(value = "delete FROM Members m WHERE m.username=:username", nativeQuery = true)
    void deleteByUsername(@Param("username") String username);
}
