package com.bib.dao.user;

import static org.junit.Assert.assertTrue;

import com.bib.BaseTest;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MembersTest extends BaseTest {

    @Autowired
    private MembersRepository membersRepository;

    @Test
    public void getAllAdmins() {
        List<Members> admins = membersRepository.findAllExistUsers().stream()
                .filter(p -> p.username.startsWith("admin"))
                .collect(Collectors.toList());
        assertTrue(admins.size() == 3);
    }
}
