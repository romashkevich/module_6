package com.romashkevich.store.controller.command.commandImpl;

import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import com.romashkevich.store.users.service.ServiceUser;
import com.romashkevich.store.users.service.ServiceUserImpl;
import com.romashkevich.store.users.service.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UsersCommand implements Command {

        private static final ServiceUser SERVICE_USER_ALL = new ServiceUserImpl();

        public String execute(HttpServletRequest req){
                try {
                    List<UserDto> userDtos = new ArrayList<>(SERVICE_USER_ALL.getAllUserDto());
                    if (userDtos==null) {
                        throw new Exception();
                    }
                    req.setAttribute("users",userDtos);
                    return "jsp/users.jsp";

                } catch (Exception e) {
                    return "jsp/error.jsp";
                }

        }
    }


