package com.romashkevich.store.controller.command.commandImpl;

import com.romashkevich.store.books.service.ServiceBook;
import com.romashkevich.store.books.service.ServiceBookImpl;
import com.romashkevich.store.books.service.dto.BookDto;
import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class BookCommand implements Command {
    private ServiceBookImpl serviceBook;

    private static final ServiceBook SERVICE_BOOK_AppAll = new ServiceBookImpl();
    @Autowired
    public void setServiceBook(ServiceBookImpl serviceBook) {
        this.serviceBook = serviceBook;
    }

    public String execute(HttpServletRequest req) {
            try {
                Long idValue = Long.parseLong(req.getParameter("id"));
                long count = (long) serviceBook.countAllBookDto();
                if (idValue >= 0 && idValue <= count) {
                    BookDto bookDto = serviceBook.getBookDtoById(idValue);
                    req.setAttribute("book", bookDto);
                    return "jsp/book.jsp";
                } else {
                    throw new SQLException();
                }

            } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
                return "jsp/error.jsp";
            }


    }
}

