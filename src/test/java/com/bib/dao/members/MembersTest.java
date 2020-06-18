package com.bib.dao.members;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.bib.BibliothekApplicationTests;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;


public class MembersTest extends BibliothekApplicationTests {

    @Autowired
    private MembersRepository membersRepository;

    @Test
    public void getAllAdmins() {
        Collection<Members> allExistUsers = membersRepository.findAllExistUsers();
        System.out.println(allExistUsers);

        List<Members> admins = allExistUsers.stream()
                .filter(p -> p.getName().startsWith("admin"))
                .collect(Collectors.toList());
        admins.forEach(System.out::println);
        assertEquals(3, admins.size());
    }

    @Test
    public void getAllAdminsEmails() {
        assertEquals(1, membersRepository.findAllExistUsers().stream()
                .filter(p -> p.getName().startsWith("admin"))
                .filter(p -> p.getEmail().endsWith("gmx.com"))
                .count());
    }

    @Test
    public void getAnAdmin() {
        Members userone = membersRepository.findByUsername("userone");
        assertEquals("one", userone.getSurname());
    }

    @Test
    public void createUser() {
        String testUser = "userthree";
        Members newUser = membersRepository.save(new Members(testUser, "pass", "userthree@gmail.com"));
        assertNotNull(newUser);
        assertEquals("userthree@gmail.com", newUser.getEmail());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void createAndDeleteUser() {
        String testUser = "user-four";
        Members newUser = membersRepository.save(new Members(testUser, "pass", "user-four@gmail.com"));
        assertNotNull(newUser);
        assertEquals("userthree@gmail.com", newUser.getEmail());

        membersRepository.deleteByUsername(testUser);

        Members deletedUser = membersRepository.findByUsername(testUser);
        assertNull(deletedUser);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteUserBySurname() {
        String testUser = "user-five";
        Members newUser = membersRepository.save(new Members(testUser, "pass", "Karl", "Marx","user-five@gmail.com"));
        assertNotNull(newUser);
        assertEquals("user-five@gmail.com", newUser.getEmail());

        membersRepository.deleteBySurname("Marx");

        Members deletedUser = membersRepository.findByUsername(testUser);
        assertNull(deletedUser);
    }
}
