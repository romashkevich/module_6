package controller.command.commandImpl;

import controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest req){
        req.setAttribute("message", "ooops");
        return "jsp/error.jsp";
    }
}
