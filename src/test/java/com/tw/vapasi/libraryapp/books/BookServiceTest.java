package com.tw.vapasi.libraryapp.books;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.awt.print.Book;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookServiceTest {
    @Autowired
    BookRepository bookRepository;

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    private BookService bookService() {
        return new BookService(bookRepository);
    }

    //ToDo Save book
    @Test
    void shouldCreateAWallet() {
        BookService bookService = new BookService(bookRepository);

        Books savedBooks = bookService.save(new Books(1L, "Life", "Sathya", "SA0", "2019"));

        assertNotNull(savedBooks);
        assertNotNull(savedBooks.getId());
        assertNotNull(savedBooks.getTitle());
        assertNotNull(savedBooks.getAuthor());
        assertNotNull(savedBooks.getIsbn());
        assertNotNull(savedBooks.getYear());
    }
    //ToDo delete
}
