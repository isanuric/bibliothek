package com.bib.dao.user;

import static org.junit.Assert.assertTrue;

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
        admins.stream().forEach(System.out::println);
        assertTrue(admins.size() == 3);
    }

    @Test
    public void getAllAdminsEmails() {
        assertTrue( membersRepository.findAllExistUsers().stream()
                .filter(p -> p.getName().startsWith("admin"))
                .filter(p -> p.getEmail().endsWith("gmx.com"))
                .collect(Collectors.toList()).size() == 1);
    }

    @Test
    public void getAnAdmin() {
        Members userone = membersRepository.findByUsername("userone");
        assertTrue("one".equals(userone.getSurname()));
    }

    @Test
    public void createUser() {
        String testUser = "userthree";
        Members newUser = membersRepository.save(new Members(testUser, "pass", "userthree@gmail.com"));
        assertTrue(newUser != null);
        assertTrue("userthree@gmail.com".equals(newUser.getEmail()));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void createAndDeleteUser() {
        String testUser = "user-four";
        Members newUser = membersRepository.save(new Members(testUser, "pass", "user-four@gmail.com"));
        assertTrue(newUser != null);
        assertTrue("userthree@gmail.com".equals(newUser.getEmail()));

        membersRepository.deleteByUsername(testUser);

        Members deletedUser = membersRepository.findByUsername(testUser);
        assertTrue(deletedUser == null);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteUserBySurname() {
        String testUser = "user-five";
        Members newUser = membersRepository.save(new Members(testUser, "pass", "Karl", "Marx","user-five@gmail.com"));
        assertTrue(newUser != null);
        assertTrue("user-five@gmail.com".equals(newUser.getEmail()));

        membersRepository.deleteBySurname("Marx");

        Members deletedUser = membersRepository.findByUsername(testUser);
        assertTrue(deletedUser == null);
    }
}
