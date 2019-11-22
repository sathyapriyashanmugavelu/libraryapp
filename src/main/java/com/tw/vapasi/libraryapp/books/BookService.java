package com.tw.vapasi.libraryapp.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Books> findUsers() {
        List<Books> books=bookRepository.findAll();
        return books;
    }
}
