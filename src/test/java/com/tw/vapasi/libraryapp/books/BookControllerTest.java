package com.tw.vapasi.libraryapp.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;


import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.mockito.Mockito.when;

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
    void shouldDisplayShowAllWithListOfBooks() throws Exception {
        Book book1 = new Book(1L,"Life1","Sathya1","SA1","2001");
        Book book2 = new Book(2L,"Life2","Sathya2","SA2","2002");
        when(bookService.findBooks()).thenReturn(Arrays.asList(book1,book2));
        mockMvc.perform(get("/books/showall"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/showall"))
                .andExpect(model().attribute("books", hasSize(2)))
                .andExpect(model().attribute("books", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("title", is("Life1")),
                                hasProperty("author", is("Sathya1")),
                                hasProperty("isbn", is("SA1")),
                                hasProperty("year", is("2001"))
                        )
                )))
                .andExpect(model().attribute("books", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("title", is("Life2")),
                                hasProperty("author", is("Sathya2")),
                                hasProperty("isbn", is("SA2")),
                                hasProperty("year", is("2002"))
                        )
                )));

        verify(bookService, times(1)).findBooks();
        verifyNoMoreInteractions(bookService);
    }

    @Test
    void shouldDisplayShow() throws Exception {
        Book book1 = new Book(2L,"Life2","Sathya2","SA2","2002");
        when(bookService.findByBookId(2L)).thenReturn(book1);
        mockMvc.perform(get("/books/show/2"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDisplayShowWithABookDetails() throws Exception {
        Book book = new Book(3L,"Life2","Sathya2","SA2","2002");
        when(bookService.findByBookId(3L)).thenReturn(book);
        mockMvc.perform(get("/books/show/3"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title", "Life2"))
                .andExpect(model().attribute("author", "Sathya2"))
                .andExpect(model().attribute("isbn","SA2"))
                .andExpect(model().attribute("year", "2002"));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        mockMvc.perform(get("/books/delete/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/showall"));
    }

    @Test
    void shouldDisplayEdit() throws Exception {
        Book book = new Book(3L,"Life2","Sathya2","SA2","2002");
        when(bookService.findByBookId(3L)).thenReturn(book);
        mockMvc.perform(get("/books/edit/3"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/edit"));
    }
}
