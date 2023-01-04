package com.romashkevich.store;

import com.romashkevich.store.books.dao.BookDaoJdbcImpl;
import com.romashkevich.store.books.dao.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

import java.util.List;

@Controller
@RequestMapping("/book")
public class ControllerBook {
    private final BookDaoJdbcImpl bookDaoJdbc;
@Autowired
    public ControllerBook(BookDaoJdbcImpl bookDaoJdbc) {
        this.bookDaoJdbc = bookDaoJdbc;
    }

    @GetMapping("/{id}")
    public String getBook(Model model, @PathVariable Long id) throws SQLException, ClassNotFoundException {
        Book book = bookDaoJdbc.getBookById(id);
        model.addAttribute("book", book);
        System.out.println(book);
        return "book";
    }
    @GetMapping("/all")
    public String getAllBook(Model model) throws SQLException, ClassNotFoundException {
        List<Book> books = bookDaoJdbc.getAllBooks();
        model.addAttribute("books", books);
        System.out.println(books);
        return "books";
    }


}
