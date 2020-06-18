package com.bib.dao.book;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.bib.BibliothekApplicationTests;
import com.bib.dao.author.Author;
import com.bib.dao.author.AuthorRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


public class AuthorRepositoryTest extends BibliothekApplicationTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void addNewAuthor() {
        Author newAuthor = authorRepository.save(new Author("Anton", "Chekhov"));
        assertNotNull(newAuthor);
        assertEquals("Chekhov", newAuthor.getSurname());
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
}