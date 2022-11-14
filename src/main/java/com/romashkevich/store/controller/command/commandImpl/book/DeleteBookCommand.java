package com.romashkevich.store.controller.command.commandImpl.book;

import com.romashkevich.store.books.service.ServiceBookImpl;
import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("delete book")
public class DeleteBookCommand implements Command {
    private ServiceBookImpl SERVICE_BOOK_AppAll;
@Autowired
    public void setSERVICE_BOOK_AppAll(ServiceBookImpl SERVICE_BOOK_AppAll) {
        this.SERVICE_BOOK_AppAll = SERVICE_BOOK_AppAll;
    }

    @Override
    public String execute(HttpServletRequest req) {
        try{
            Long idValue = Long.parseLong(req.getParameter("id"));
            Boolean answer = SERVICE_BOOK_AppAll.deleteBookDto(idValue);
            req.setAttribute("answer",answer);
            req.setAttribute("id",idValue);
            return "jsp/deleteBook.jsp";
        }catch (Exception e) {
            return "jsp/error.jsp";
        }
    }
}
