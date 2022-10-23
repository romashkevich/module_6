package controller.command.commandImpl;

import controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import users.dao.UserDaoJdbcImpl;
import users.dbServiceUsers.DbConnectUser;
import users.service.ServiceUser;
import users.service.ServiceUserImpl;
import users.service.dto.UserDto;

public class UserCommand implements Command {
        private static final ServiceUser SERVICE_USER_ALL = new ServiceUserImpl(new UserDaoJdbcImpl(new DbConnectUser()));

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


