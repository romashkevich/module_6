package com.romashkevich.store.controller.command.commandImpl.user;

import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import com.romashkevich.store.users.service.ServiceUser;
import com.romashkevich.store.users.service.ServiceUserImpl;
import com.romashkevich.store.users.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("users")
public class UsersCommand implements Command {
    private ServiceUserImpl serviceUser;
    @Autowired
    public void setServiceUser(ServiceUserImpl serviceUser) {
        this.serviceUser = serviceUser;
    }

    public String execute(HttpServletRequest req) {
        try {
            List<UserDto> userDtos = new ArrayList<>(serviceUser.getAllUserDto());
            if (userDtos.isEmpty()) {
                throw new Exception();
            }
            req.setAttribute("users", userDtos);
            return "jsp/users.jsp";

        } catch (Exception e) {
            return "jsp/error.jsp";
        }

    }
}


