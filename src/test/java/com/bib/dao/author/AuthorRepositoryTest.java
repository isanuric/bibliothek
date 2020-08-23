package com.bib.dao.author;

import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.bib.BibliothekApplicationTests;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


public class AuthorRepositoryTest extends BibliothekApplicationTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Ignore
    @Test
    public void ceateRandomAuthor() {
        range(1, 100).forEach(i -> {
            final var random = RandomStringUtils.random(5, true, false).toUpperCase();
            authorRepository.save(new Author("Author-" + random, random));
        });
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteAuthor() {
        Author newAuthor = authorRepository.save(new Author("Franz", "Kafka"));
        assertNotNull(newAuthor);
        Integer id = newAuthor.getId();

        authorRepository.deleteBySurname("Kafka");
        assertTrue(authorRepository.findById(id).isEmpty());
    }

    @Test
    public void findAllBooksOfAuthor() {
//        final var user = authorRepository.getBooksBySurname("KSRXB");
        final Set<Author> user = authorRepository.getAuthorAndBooks("KSRXB");
        for (Author v : user) {
            System.out.println(v.getBook().get(0).getName());
            System.out.println(v.getBook().get(1).getName());
            System.out.println(v.getBook().size());
//            System.out.println( v.get(0).toString());
        }
//        assertTrue(user.stream()
//                .map(p -> p.get(0).equals("Was heisst Denken?"))
//                .findFirst().get());
    }
}
