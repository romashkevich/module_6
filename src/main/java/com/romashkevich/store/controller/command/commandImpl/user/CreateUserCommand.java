package com.romashkevich.store.controller.command.commandImpl.user;

import com.romashkevich.store.controller.command.Command;
import com.romashkevich.store.users.service.ServiceUserImpl;
import com.romashkevich.store.users.service.dto.AdressDto;
import com.romashkevich.store.users.service.dto.RoleDto;
import com.romashkevich.store.users.service.dto.SexDto;
import com.romashkevich.store.users.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("create user")
public class CreateUserCommand implements Command {
    private ServiceUserImpl serviceUser;

    @Autowired
    public void setServiceUser(ServiceUserImpl serviceUser) {
        this.serviceUser = serviceUser;
    }

    @Override
    public String execute(HttpServletRequest req) {
        try {
            UserDto userDto = serviceUser.createUserDto(setUserDto(req));
            req.setAttribute("user",userDto);
            return "jsp/createUser.jsp";
        } catch (Exception e){
            return "jsp/error.jsp";
        }
    }

    private UserDto setUserDto(HttpServletRequest req) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(req.getParameter("firstName"));
        userDto.setLastName(req.getParameter("lastName"));
        userDto.setEmail(req.getParameter("email"));
        userDto.setLogin(req.getParameter("login"));
        userDto.setPassword(req.getParameter("password"));
        userDto.setTelNum(req.getParameter("telNumber"));
        userDto.setSexDto(sexDto(req));
        userDto.setRoleDto(roleDto(req));
        userDto.setAdress(adressDto(req));

        return userDto;
    }

    private SexDto sexDto(HttpServletRequest req) {
        String sex = req.getParameter("SEX");
        if(sex.equals("MAN")){
            return SexDto.MAN;
        }
        return SexDto.WOMAN;
    }
    private RoleDto roleDto(HttpServletRequest req){
        String roleDto = req.getParameter("ROLE");
        switch (roleDto) {
            case ("ADMIN"):
                return RoleDto.ADMIN;
            case ("MNGR"):
                return RoleDto.MNGR;
            default:
                return RoleDto.CUST;

        }

    }
    private AdressDto adressDto(HttpServletRequest req){
        AdressDto adressDto = new AdressDto();
        adressDto.setCountry(req.getParameter("country"));
        adressDto.setCity(req.getParameter("city"));
        adressDto.setStreet(req.getParameter("street"));
        adressDto.setStrNum(Integer.parseInt(req.getParameter("strNum")));
        adressDto.setApart(Integer.parseInt(req.getParameter("apart")));
        return adressDto;
    }
}
