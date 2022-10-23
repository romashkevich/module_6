package com.romashkevich.store.controller.command.commandImpl;

import com.romashkevich.store.books.service.ServiceBook;
import com.romashkevich.store.books.service.ServiceBookImpl;
import com.romashkevich.store.books.service.dto.BookDto;
import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

public class CreateCommand implements Command {
    private static final ServiceBook SERVICE_BOOK_AppAll = new ServiceBookImpl();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            BookDto bookDto = SERVICE_BOOK_AppAll.createBookDto(setBookDto(req));
            req.setAttribute("book", bookDto);
            return "jsp/createBook.jsp";
        } catch (Exception e) {
            return "jsp/error.jsp";
        }
    }

    private static BookDto setBookDto(HttpServletRequest req) {
        BookDto book1 = new BookDto();
        book1.setIsbn(req.getParameter("isbn"));
        book1.setTitle(req.getParameter("title"));
        book1.setAuthor(req.getParameter("author"));
        book1.setPages(Integer.parseInt(req.getParameter("pages")));
        book1.setCover(req.getParameter("cover"));
        book1.setPrice(BigDecimal.valueOf(Long.parseLong(req.getParameter("price"))));
        return book1;
    }
}
