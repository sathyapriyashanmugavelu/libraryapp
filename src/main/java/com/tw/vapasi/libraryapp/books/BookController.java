package com.tw.vapasi.libraryapp.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    public String showBooks(Model model){
        List<Books> books = bookService.findUsers();
        model.addAttribute("books",books);
        return "books/show";
    }




}
