package users.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import users.dao.UserDaoJdbcImpl;
import users.dao.entity.Adress;
import users.dao.entity.Role;
import users.dao.entity.Sex;
import users.dao.entity.User;
import users.service.dto.AdressDto;
import users.service.dto.RoleDto;
import users.service.dto.SexDto;
import users.service.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceUserImpl implements ServiceUser {

    private UserDaoJdbcImpl userDao;
    private static final Logger loger = LogManager.getLogger("run main.books.service method");

    public void setUserDao(UserDaoJdbcImpl userDao) {
        this.userDao = userDao;
    }

    public List<UserDto> getAllUserDto() throws SQLException, ClassNotFoundException {
        loger.debug("");
        List<User> users = new ArrayList<>();
        List<UserDto> userDtoList = new ArrayList<>();
        users = userDao.getAllUser();
        if (users != null) {
            userDtoList = users.stream()
                    .map(user -> toUserDto(user))
                    .collect(Collectors.toList());
        }
        return userDtoList;
    }

    @Override
    public UserDto getUserDtoById(Long id) throws SQLException, ClassNotFoundException {

        loger.debug("");
        User user;
        UserDto userDto = new UserDto();

        user = userDao.getUserById(id);
        if (user != null) {
            userDto = toUserDto(user);
        }

        return userDto;
    }

    private UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        AdressDto adressDto = new AdressDto();

        adressDto.setCountry(user.getAdress().getCountry());
        adressDto.setCity(user.getAdress().getCity());
        adressDto.setStreet(user.getAdress().getStreet());
        adressDto.setStrNum(user.getAdress().getStrNum());
        adressDto.setApart(user.getAdress().getApart());

        userDto.setId(user.getId());
        userDto.setRoleDto(toRoleDto(user.getRoleClass()));
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(userDto.getEmail());
        userDto.setAdress(adressDto);
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setTelNum(user.getTelNum());
        userDto.setSexDto(toSexDto(user.getSexClass()));
        return userDto;
    }

    private User toUser(UserDto userDto) {
        User user = new User();
        Adress adress = new Adress();

        adress.setCountry(userDto.getAdress().getCountry());
        adress.setCity(userDto.getAdress().getCity());
        adress.setStreet(userDto.getAdress().getStreet());
        adress.setStrNum(userDto.getAdress().getStrNum());
        adress.setApart(userDto.getAdress().getApart());

        user.setId(userDto.getId());
        user.setRole(toRole(userDto.getRoleDtoClass()));
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAdress(adress);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setTelNum(userDto.getTelNum());
        user.setSex(toSex(userDto.getSexDtoClass()));
        return user;
    }

    private SexDto toSexDto(Sex sex) {
        return sex.name().equals("MAN") ? SexDto.MAN : SexDto.WOMAN;
    }

    private Sex toSex(SexDto sexDto) {
        return sexDto.name().equals("MAN") ? Sex.MAN : Sex.WOMAN;
    }

    private RoleDto toRoleDto(Role role) {
        RoleDto roleDto;
        switch (role.name()) {
            case ("ADMIN"):
                roleDto = RoleDto.ADMIN;
                break;
            case ("MNGR"):
                roleDto = RoleDto.MNGR;
                break;
            default:
                roleDto = RoleDto.CUST;
                break;
        }
        return roleDto;
    }

    private Role toRole(RoleDto roleDto) {
        Role role;
        switch (roleDto.name()) {
            case ("ADMIN"):
                role = Role.ADMIN;
                break;
            case ("MNGR"):
                role = Role.MNGR;
                break;
            default:
                role = Role.CUST;
                break;
        }
        return role;

    }

    @Override
    public UserDto createUserDto(UserDto uDto) throws SQLException, ClassNotFoundException {
        loger.debug("");
        UserDto userDto = new UserDto();
        User user1 = toUser(uDto);
        userDto = toUserDto(userDao.createUser(user1));
        return userDto;
    }

    @Override
    public UserDto updateUserDto(UserDto user) throws SQLException, ClassNotFoundException {
        loger.log(Level.DEBUG, "");
        UserDto userDto = new UserDto();
        User user1 = toUser(user);
        userDto = toUserDto(userDao.updateUser(user1));
        return userDto;
    }

    @Override
    public void deleteUserDto(Long id) throws SQLException, ClassNotFoundException {
        loger.debug("");
        userDao.deleteUser(id);

    }

    @Override
    public UserDto getUserDtoByEmail(String email) throws SQLException, ClassNotFoundException {
        loger.debug("");
        UserDto userDto = new UserDto();
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            userDto = toUserDto(user);
        }
        return userDto;
    }

    @Override
    public List<UserDto> getUsersDtoByLastName(String lastName) throws SQLException, ClassNotFoundException {
        loger.debug("");
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = userDao.getUsersByLastName(lastName);
        if (!users.isEmpty()) {
            userDtoList = users.stream()
                    .map(this::toUserDto)
                    .collect(Collectors.toList());
        }

        return userDtoList;
    }

    @Override
    public int countAllUsersDto() throws SQLException, ClassNotFoundException {
        loger.debug("");
        int count = 0;
        List<UserDto> usersDto = getAllUserDto();
        if (!usersDto.isEmpty()) {
            count = usersDto.size();
        }
        return count;
    }

    @Override
    public UserDto getUserDtoByLolin(String login) throws SQLException, ClassNotFoundException {
        loger.debug("");
        User user;
        UserDto userDto = new UserDto();
        user = userDao.getUserByLogin(login);
        userDto = toUserDto(user);
        return userDto;
    }
}
