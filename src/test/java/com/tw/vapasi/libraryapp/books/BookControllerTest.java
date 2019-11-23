package com.tw.vapasi.libraryapp.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;


import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.mockito.Mockito.when;
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
    void shouldDisplayShowAllWithListOfBooks() throws Exception {
        Books book1 = new Books(1L,"Life1","Sathya1","SA1","2001");
        Books book2 = new Books(2L,"Life2","Sathya2","SA2","2002");
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
        Books book1 = new Books(2L,"Life2","Sathya2","SA2","2002");
        when(bookService.findByBookId(2L)).thenReturn(book1);
        mockMvc.perform(get("/books/show/2"))
                .andExpect(status().isOk());
    }
}
