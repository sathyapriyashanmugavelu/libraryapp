package com.tw.vapasi.libraryapp.books;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        Book book1 = bookRepository.save(new Book(1L, "Life1", "Sathya1", "SA1", "2001"));
        Book book2 = bookRepository.save(new Book(2L, "Life2", "Sathya2", "SA2", "2002"));
        BookService bookService = new BookService(bookRepository);
        List<Book> books = bookService().findBooks();

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
        Book savedBook = bookRepository.save(new Book(1L, "Life", "Sathya", "SA0", "2019"));
        BookService bookService = new BookService(bookRepository);
        Book book = bookService.findByBookId(savedBook.getId());

        assertEquals("Life", book.getTitle());
        assertEquals("Sathya", book.getAuthor());
        assertEquals("SA0", book.getIsbn());
        assertEquals("2019", book.getYear());
    }

    @Test
    void shouldUpdateABook() {
        BookService bookService = new BookService(bookRepository);
        Book savedBook = bookService.save(new Book(1L, "Life", "Sathya", "SA0", "2019"));

        assertNotNull(savedBook);
        assertNotNull(savedBook.getId());
        assertNotNull(savedBook.getTitle());
        assertNotNull(savedBook.getAuthor());
        assertNotNull(savedBook.getIsbn());
        assertNotNull(savedBook.getYear());
    }

    @Test
    void shouldDeleteABook() {
        BookService bookService = new BookService(bookRepository);
        bookService.deleteBook(new Book(1L, "Life", "Sathya", "SA0", "2019"));

        assertThrows(IllegalArgumentException.class, () -> bookService.findByBookId(1L));
    }
}
