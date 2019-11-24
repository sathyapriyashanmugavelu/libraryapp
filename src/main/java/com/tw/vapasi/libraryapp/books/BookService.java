package com.tw.vapasi.libraryapp.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public Book findByBookId(long id) {
        Book book = bookRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Invalid Book Id:" + id));
        return book;
    }

    public Book save(Book book) {
        Book saveBook = bookRepository.save(book);
        return saveBook;
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
