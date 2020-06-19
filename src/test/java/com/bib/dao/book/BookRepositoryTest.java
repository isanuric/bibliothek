package com.bib.dao.book;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.bib.BibliothekApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class BookRepositoryTest extends BibliothekApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getAllBooks() {
        assertTrue(bookRepository.getAllBooks().size() > 30);
    }

    @Test
    public void createBook() {
        String testBookName = "Test Book I";
        Book testBookI = bookRepository.save(new Book(1, testBookName));

        assertNotNull(testBookI);
        assertEquals(testBookName, testBookI.getName());
    }

    @Test
    public void deleteBook() {
        String testBookName = "Test Book II";
        Book testBook = bookRepository.save(new Book(1, "Test Book II"));
        Integer bookId = testBook.getId();

        bookRepository.deleteByName(testBookName);

        assertNull(bookRepository.findById(bookId));
    }
}