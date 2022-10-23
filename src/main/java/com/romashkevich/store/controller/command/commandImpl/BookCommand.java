package com.romashkevich.store.controller.command.commandImpl;

import com.romashkevich.store.books.service.ServiceBook;
import com.romashkevich.store.books.service.ServiceBookImpl;
import com.romashkevich.store.books.service.dto.BookDto;
import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class BookCommand implements Command {

    private static final ServiceBook SERVICE_BOOK_AppAll = new ServiceBookImpl();

    public String execute(HttpServletRequest req) {
            try {
                Long idValue = Long.parseLong(req.getParameter("id"));
                long count = (long) SERVICE_BOOK_AppAll.countAllBookDto();
                if (idValue >= 0 && idValue <= count) {
                    BookDto bookDto = SERVICE_BOOK_AppAll.getBookDtoById(idValue);
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

