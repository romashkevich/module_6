package com.romashkevich.store.controller.command.commandImpl;

import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component("errorCommand")
public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest req){
        req.setAttribute("message", "ooops");
        return "jsp/error.jsp";
    }
}
