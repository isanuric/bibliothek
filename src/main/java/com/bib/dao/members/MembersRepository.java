package com.bib.dao.members;


import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 */

@Repository
public interface MembersRepository extends CrudRepository<Members, Integer> {

    @Query(value = "SELECT m FROM Members m")
    Collection<Members> findAllExistUsers();

//    @Query(value = "SELECT m FROM Members m WHERE m.username = :username", nativeQuery = true)
//    @Query(value = "SELECT * FROM Members m WHERE m.username = :username")
    Members findByUsername(@Param("username") String username);

    @Modifying
    void deleteByUsername(@Param("username") String username);

    void deleteBySurname(String surname);
}
