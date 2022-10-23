package com.romashkevich.store.controller.command.commandImpl;

import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import com.romashkevich.store.users.service.ServiceUser;
import com.romashkevich.store.users.service.ServiceUserImpl;
import com.romashkevich.store.users.service.dto.UserDto;

public class UserCommand implements Command {
        private static final ServiceUser SERVICE_USER_ALL = new ServiceUserImpl();

        public String execute(HttpServletRequest req){
                try {
                    Long idValue = Long.parseLong(req.getParameter("id"));
                    long count = (long) SERVICE_USER_ALL.countAllUsersDto();
                    if (idValue >= 0 && idValue <= count) {
                        UserDto userDto = SERVICE_USER_ALL.getUserDtoById(idValue);
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


