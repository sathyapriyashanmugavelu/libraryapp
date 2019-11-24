package com.tw.vapasi.libraryapp.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/showall")
    public String showBooks(Model model) {
        List<Books> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "books/showall";
    }

    @RequestMapping("/show/{id}")
    String show(@PathVariable long id, Model model) {
        Books books = bookService.findByBookId(id);
        model.addAttribute("title", books.getTitle());
        model.addAttribute("isbn", books.getIsbn());
        model.addAttribute("year", books.getYear());
        model.addAttribute("author", books.getAuthor());
        return "books/show";
    }

    @RequestMapping("/edit/{id}")
    String showUpdateForm(@PathVariable long id, Model model) {
        Books books = bookService.findByBookId(id);
        model.addAttribute("books", books);
        return "books/edit";
    }

    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST,params = "submit")
    String updateUser(Model model, @ModelAttribute Books books) {
        bookService.save(books);
        model.addAttribute("books", bookService.findBooks());
        return "books/showall";
    }

    @RequestMapping(value="/edit/{id}",method = RequestMethod.POST,params = "cancel")
    String cancelUpdateUser(Model model, @ModelAttribute Books books) {
        model.addAttribute("books", bookService.findBooks());
        return "books/showall";
    }

    @RequestMapping("/delete/{id}")
    String deleteUser(@PathVariable long id, Model model) {
        bookService.deleteBook(bookService.findByBookId(id));
        model.addAttribute("books", bookService.findBooks());
        return "books/showall";
    }

    @RequestMapping("/add")
    String addBook(Model model) {
        Books books = new Books();
        model.addAttribute("books", books);
        return "books/add";
    }
    @PostMapping("/add")
    String saveAddBooks(@ModelAttribute Books books, Model model) {
        bookService.save(books);
        model.addAttribute("books", bookService.findBooks());
        return "books/showall";
    }
}
