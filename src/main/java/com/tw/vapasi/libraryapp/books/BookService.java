package com.tw.vapasi.libraryapp.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Books> findBooks() {
        List<Books> books = bookRepository.findAll();
        return books;
    }

    public Books findByBookId(long id) {
        Books book = bookRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Invalid Book Id:" + id));
        return book;
    }

    public Books create(Books book) {
        Books saveBook = bookRepository.save(book);
        return saveBook;
    }

    public void deleteBook(Books books) {
        bookRepository.delete(books);
    }
}
