package com.bib.dao.members;

import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.bib.BibliothekApplicationTests;
import javax.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;


public class MembersTest extends BibliothekApplicationTests {

    @Autowired
    private MembersRepository membersRepository;

    @Ignore
    @Test
    public void createRandomAdmins() {
        range(1, 20).forEach( i -> {
            final var random = RandomStringUtils.random(5, true, false).toUpperCase();
            final var username = "admin-" + random;
            membersRepository.save(new Members(
                    username,
                    "pass-" + random,
                    "name-" + random,
                    random,
                    username + "@gmail.com"));
        });
    }

    @Test
    public void getAdmins() {
        final var numberOfadmins = membersRepository.findAllExistUsers().stream()
                .filter(p -> p.getUsername().startsWith("admin"))
                .count();
        assertTrue(numberOfadmins > 10);
    }

    @Test
    public void getAdminsEmails() {
        assertTrue( membersRepository.findAllExistUsers().stream()
                .filter(p -> p.getUsername().startsWith("admin"))
                .filter(p -> p.getEmail().endsWith("gmail.com"))
                .count() > 10);
    }

    @Test
    public void getAnAdmin() {
        Members userone = membersRepository.findByUsername("adminOne");
        assertEquals("one", userone.getSurname());
    }

    @Test
    public void createUser() {
        String testUser = "userthree";
        Members newUser = membersRepository.save(new Members(testUser, "pass", "userthree@gmail.com"));
        assertNotNull(newUser);
        assertEquals(testUser + "@gmail.com", newUser.getEmail());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void createAndDeleteUser() {
        String testUser = "user-four";
        final var newMember = new Members(testUser, "pass", "test", "tester", "user-four@gmail.com");
        Members newUser = membersRepository.save(newMember);
        assertNotNull(newUser);
        assertEquals(testUser + "@gmail.com", newUser.getEmail());

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

        assertNull(membersRepository.findByUsername(testUser));
    }
}
