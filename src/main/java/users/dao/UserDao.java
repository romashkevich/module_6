package users.dao;

import users.dao.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> getAllUser() throws SQLException, ClassNotFoundException;

    User getUserById(Long id) throws SQLException, Exception;

    User createUser(User user) throws Exception; // передаем книгу в базу данных и создаем строку с ее данными

    User updateUser(User user) throws SQLException, ClassNotFoundException;// замена инфы передаваемой книги и возврат измененных значений

    boolean deleteUser(Long id) throws SQLException, ClassNotFoundException;// передача в бд книги ее поиск и дальнейшая пометка удалена

    User getUserByEmail(String email) throws SQLException, ClassNotFoundException;

    List<User> getUsersByLastName(String firstName) throws SQLException, ClassNotFoundException;

    int countAllUsers() throws SQLException, ClassNotFoundException;

    User getUserByLogin(String login) throws SQLException, ClassNotFoundException;
}
