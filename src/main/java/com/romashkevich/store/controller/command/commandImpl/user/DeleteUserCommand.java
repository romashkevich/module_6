package com.romashkevich.store.controller.command.commandImpl.user;

import com.romashkevich.store.controller.command.Command;
import com.romashkevich.store.users.service.ServiceUserImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("delete user")
public class DeleteUserCommand implements Command {
    private ServiceUserImpl serviceUser;

    @Autowired
    public void setServiceUser(ServiceUserImpl serviceUser) {
        this.serviceUser = serviceUser;
    }

    @Override
    public String execute(HttpServletRequest req) {
        try {
            Long idValue = Long.valueOf(req.getParameter("id"));
            Boolean answer = serviceUser.deleteUserDto(idValue);
            req.setAttribute("id", idValue);
            req.setAttribute("answer", answer);
        } catch (Exception e) {
            return "jsp/error.jsp";
        }

            return null;
        }
    }
