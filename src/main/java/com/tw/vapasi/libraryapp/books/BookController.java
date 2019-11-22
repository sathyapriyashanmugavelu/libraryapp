package com.tw.vapasi.libraryapp.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/showall")
    public String showBooks(Model model){
        List<Books> books = bookService.findBooks();
        model.addAttribute("books",books);
        return "books/showall";
    }

    @RequestMapping("/show/{id}")
    String show(@PathVariable long id,Model model){
        Books books=bookService.findByBookId(id);
        System.out.println("Books:"+books.getId());
        model.addAttribute("title", books.getTitle());
        model.addAttribute("isbn", books.getIsbn());
        model.addAttribute("date", books.getDate());
        model.addAttribute("author", books.getAuthor());
        return "books/show";
    }

//
//    @RequestMapping(value="/show/{id}", params="action=Cancel")
//    String cancel(){
//        return "books/show";
//    }

    @RequestMapping("/edit/{id}")
    String showUpdateForm(@PathVariable long id, Model model) {
        Books books = bookService.findByBookId(id);
        model.addAttribute("books", books);
        return "books/edit";
    }

    @PostMapping("/edit/{id}")
    String updateUser(@PathVariable long id,Model model,@ModelAttribute Books books){
        bookService.create(books);
        System.out.println("Edit :"+books);
        model.addAttribute("users",bookService.findBooks());
        return "books/showall";
    }

    @RequestMapping("/delete/{id}")
    String deleteUser(@PathVariable("id") long id, Model model) {
        Books books = bookService.findByBookId(id);
        bookService.deleteBook(books);
        model.addAttribute("users", bookService.findBooks());
        return "books/showall";
    }
}
