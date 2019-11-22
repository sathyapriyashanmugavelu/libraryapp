package com.tw.vapasi.libraryapp.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;


    @Test
    void shouldDisplayShowAll() throws Exception {
        mockMvc.perform(get("/books/showall"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/showall"));
    }

    @Test
    void shouldDisplayShow() throws Exception {
        mockMvc.perform(get("/books/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/show/1"));
    }
    @Test
    void shouldDeleteBook() throws Exception {
        mockMvc.perform(get("/books/delete/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/showall"));
    }
}
