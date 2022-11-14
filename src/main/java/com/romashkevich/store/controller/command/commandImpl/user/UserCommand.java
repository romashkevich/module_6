package com.romashkevich.store.controller.command.commandImpl.user;

import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import com.romashkevich.store.users.service.ServiceUser;
import com.romashkevich.store.users.service.ServiceUserImpl;
import com.romashkevich.store.users.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("user")
public class UserCommand implements Command {
        private ServiceUserImpl serviceUser;

    @Autowired
    public void setServiceUser(ServiceUserImpl serviceUser) {
        this.serviceUser = serviceUser;
    }

    public String execute(HttpServletRequest req){
                try {
                    Long idValue = Long.parseLong(req.getParameter("id"));
                    long count = (long) serviceUser.countAllUsersDto();
                    if (idValue >= 0 && idValue <= count) {
                        UserDto userDto = serviceUser.getUserDtoById(idValue);
                        req.setAttribute("user",userDto);
                        return "jsp/user.jsp";
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    return "jsp/error.jsp";
                }

        }
    }


