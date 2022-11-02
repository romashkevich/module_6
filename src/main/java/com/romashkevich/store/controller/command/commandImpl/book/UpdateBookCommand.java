package com.romashkevich.store.controller.command.commandImpl.book;

import com.romashkevich.store.books.dao.BookDaoJdbcImpl;
import com.romashkevich.store.books.service.ServiceBookImpl;
import com.romashkevich.store.books.service.dto.BookDto;
import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("update book")
public class UpdateBookCommand implements Command {
    private ServiceBookImpl serviceBook;
@Autowired
    public void setServiceBook(ServiceBookImpl serviceBook) {
        this.serviceBook = serviceBook;
    }

    @Override
    public String execute(HttpServletRequest req) {
        try {
            BookDto bookDto = serviceBook.updateBookDto(setBookDto(req));
            req.setAttribute("book", bookDto);
            return "jsp/updateBook.jsp";
        } catch (Exception e){
            return "jsp/error.jsp";
        }
    }
    private BookDto setBookDto(HttpServletRequest req) {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(req.getParameter("author"));
        bookDto.setIsbn(req.getParameter("isbn"));
        bookDto.setTitle(req.getParameter("title"));
        bookDto.setCover(req.getParameter("cover"));
        bookDto.setPages(Integer.parseInt(req.getParameter("pages")));
        bookDto.setPrice(BigDecimal.valueOf(Long.parseLong(req.getParameter("price"))));
        return bookDto;
    }
}
