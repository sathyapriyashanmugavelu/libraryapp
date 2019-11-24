package com.tw.vapasi.libraryapp.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookshelf")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    public String showBooks(Model model) {
        List<Book> book = bookService.findBooks();
        model.addAttribute("book", book);
        return "books/showall";
    }

    @RequestMapping("/book/{id}/show")
    String showABook(@PathVariable long id, Model model) {
        Book book = bookService.findByBookId(id);
        model.addAttribute("title", book.getTitle());
        model.addAttribute("isbn", book.getIsbn());
        model.addAttribute("year", book.getYear());
        model.addAttribute("author", book.getAuthor());
        return "books/show";
    }

    @RequestMapping("/book/{id}/edit")
    String showUpdate(@PathVariable long id, Model model) {
        Book book = bookService.findByBookId(id);
        model.addAttribute("book", book);
        return "books/edit";
    }

    @RequestMapping(value="/book/{id}/edit",method = RequestMethod.POST,params = "submit")
    String updateBook(Model model, @ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/bookshelf/books";
    }

    @RequestMapping(value="/book/{id}/edit",method = RequestMethod.POST,params = "cancel")
    String cancelUpdateBook(Model model, @ModelAttribute Book book) {
        return "redirect:/bookshelf/books";
    }

    @RequestMapping("/book/{id}/delete")
    String deleteBook(@PathVariable long id, Model model) {
        bookService.deleteBook(bookService.findByBookId(id));
        return "redirect:/bookshelf/books";
    }

    @RequestMapping("/book/add")
    String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "books/add";
    }
    @PostMapping("/book/add")
    String saveAddBook(@ModelAttribute Book book, Model model) {
        bookService.save(book);
        return "redirect:/bookshelf/books";
    }
}
