package com.bib.dao.user;

import static org.junit.Assert.assertTrue;

import com.bib.BibliothekApplicationTests;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


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
        assertTrue(admins.size() == 3);
    }

    @Test
    public void getAllAdminsEmails() {
        Collection<Members> allExistUsers = membersRepository.findAllExistUsers();
        System.out.println(allExistUsers);

        List<Members> admins = allExistUsers.stream()
                .filter(p -> p.getName().startsWith("admin"))
                .filter(p -> p.getEmail().endsWith("gmx.com"))
                .collect(Collectors.toList());
        admins.stream().forEach(System.out::println);
        assertTrue(admins.size() == 1);
    }
}
