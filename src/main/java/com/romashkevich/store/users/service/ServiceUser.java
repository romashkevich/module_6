package com.romashkevich.store.users.service;

import com.romashkevich.store.users.service.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface ServiceUser {
    public List<UserDto> getAllUserDto() throws SQLException, ClassNotFoundException;

    public UserDto getUserDtoById(Long id) throws SQLException, ClassNotFoundException;

    public UserDto createUserDto(UserDto user) throws SQLException, ClassNotFoundException;

    public UserDto updateUserDto(UserDto user) throws SQLException, ClassNotFoundException;

    public void deleteUserDto(Long id) throws SQLException, ClassNotFoundException;

    public UserDto getUserDtoByEmail(String email) throws SQLException, ClassNotFoundException;

    public List<UserDto> getUsersDtoByLastName(String lastName) throws SQLException, ClassNotFoundException;

    public int countAllUsersDto() throws SQLException, ClassNotFoundException;

    UserDto getUserDtoByLolin(String login) throws SQLException, ClassNotFoundException;

}
