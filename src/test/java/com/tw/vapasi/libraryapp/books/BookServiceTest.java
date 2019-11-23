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

    @Test
    void fetchAllBook() throws Exception {
        Books book1 = bookRepository.save(new Books(1L, "Life1", "Sathya1", "SA1", "2001"));
        Books book2 = bookRepository.save(new Books(2L, "Life2", "Sathya2", "SA2", "2002"));
        BookService bookService = new BookService(bookRepository);
        List<Books> books = bookService().findBooks();

        assertEquals(3L, books.get(0).getId());
        assertEquals("Life1", books.get(0).getTitle());
        assertEquals("Sathya1", books.get(0).getAuthor());
        assertEquals("SA1", books.get(0).getIsbn());
        assertEquals("2001", books.get(0).getYear());

        assertEquals(4L, books.get(1).getId());
        assertEquals("Life2", books.get(1).getTitle());
        assertEquals("Sathya2", books.get(1).getAuthor());
        assertEquals("SA2", books.get(1).getIsbn());
        assertEquals("2002", books.get(1).getYear());
    }

    @Test
    void fetchABook() throws Exception {
        Books savedBook = bookRepository.save(new Books(1L, "Life", "Sathya", "SA0", "2019"));
        BookService bookService = new BookService(bookRepository);
        Books books = bookService.findByBookId(savedBook.getId());

        assertEquals("Life", books.getTitle());
        assertEquals("Sathya", books.getAuthor());
        assertEquals("SA0", books.getIsbn());
        assertEquals("2019", books.getYear());
    }

    @Test
    void shouldUpdateABook() {
        BookService bookService = new BookService(bookRepository);
        Books savedBooks = bookService.save(new Books(1L, "Life", "Sathya", "SA0", "2019"));

        assertNotNull(savedBooks);
        assertNotNull(savedBooks.getId());
        assertNotNull(savedBooks.getTitle());
        assertNotNull(savedBooks.getAuthor());
        assertNotNull(savedBooks.getIsbn());
        assertNotNull(savedBooks.getYear());
    }

    @Test
    void shouldDeleteABook() {
        BookService bookService = new BookService(bookRepository);
        bookService.deleteBook(new Books(1L, "Life", "Sathya", "SA0", "2019"));

        assertThrows(IllegalArgumentException.class, () -> bookService.findByBookId(1L));
    }
}
