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

    @RequestMapping
    public String showBooks(Model model) {
        List<Book> book = bookService.findBooks();
        model.addAttribute("book", book);
        return "books/showall";
    }

    @RequestMapping("/{id}/show")
    String showABook(@PathVariable long id, Model model) {
        Book book = bookService.findByBookId(id);
        model.addAttribute("title", book.getTitle());
        model.addAttribute("isbn", book.getIsbn());
        model.addAttribute("year", book.getYear());
        model.addAttribute("author", book.getAuthor());
        return "books/show";
    }

    @RequestMapping("/{id}/edit")
    String showUpdate(@PathVariable long id, Model model) {
        Book book = bookService.findByBookId(id);
        model.addAttribute("book", book);
        return "books/edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST, params = "submit")
    String updateBook(Model model, @ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST, params = "cancel")
    String cancelUpdateBook(Model model, @ModelAttribute Book book) {
        return "redirect:/books";
    }

    @RequestMapping("/{id}/delete")
    String deleteBook(@PathVariable long id, Model model) {
        bookService.deleteBook(bookService.findByBookId(id));
        return "redirect:/books";
    }

    @RequestMapping("/add")
    String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "books/add";
    }

    @PostMapping("/add")
    String saveAddBook(@ModelAttribute Book book, Model model) {
        bookService.save(book);
        return "redirect:/books";
    }
}
