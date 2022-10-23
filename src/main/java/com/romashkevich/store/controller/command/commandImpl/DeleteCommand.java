package com.romashkevich.store.controller.command.commandImpl;

import com.romashkevich.store.books.service.ServiceBook;
import com.romashkevich.store.books.service.ServiceBookImpl;
import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteCommand implements Command {
    private static final ServiceBook SERVICE_BOOK_AppAll = new ServiceBookImpl();

    @Override
    public String execute(HttpServletRequest req) {
        try{
            Long idValue = Long.parseLong(req.getParameter("id"));
            Boolean answer = SERVICE_BOOK_AppAll.deleteBookDto(idValue);
            req.setAttribute("answer",answer);
            req.setAttribute("id",idValue);
            return "jsp/delete.jsp";
        }catch (Exception e) {
            return "jsp/error.jsp";
        }
    }
}
