package users.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import users.dao.entity.Adress;
import users.dao.entity.Role;
import users.dao.entity.Sex;
import users.dao.entity.User;
import users.dbServiceUsers.DbConnectUser;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJdbcImpl implements UserDao {
    public static final String SELECT_USERS = "SELECT * FROM users WHERE deleted = false";
    public static final String SELECT_SEX = "SELECT * FROM sex WHERE  sex_id = ? ";
    public static final String SELECT_ADRESS_ID = "SELECT * FROM adress WHERE adress_id = ?";
    public static final String SELECT_ADRESS_ID_2 = "SELECT adress_id FROM users WHERE login = ? AND deleted = false";
    public static final String SELECT_MAX_ADRESS_ID = "SELECT MAX(adress_id) FROM adress";
    public static final String SELECT_ID = "SELECT * FROM users WHERE id = ? AND deleted = false";
    public static final String SELECT_LOGIN = "SELECT * FROM users WHERE login = ? AND deleted = false";
    public static final String UPDATE_USER = "UPDATE users SET role_id=?, pass = ?, firstname=?, lastname=?, telnumber=?, sex_id=? WHERE login=?";
    public static final String UPDATE_ADRESS = "UPDATE adress SET country = ?, city=?, street=?, strNum=?, apart=? WHERE adress_id=?";
    public static final String INSERT_ADRESS = "INSERT INTO adress (country, city, street, strNum, apart) VALUES(?,?,?,?,?)";
    public static final String CREATE_USER = "INSERT INTO users (role_id, login, email, pass, firstname, lastname, adress_id, telnumber, sex_id) VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_USERS_DELETE = "UPDATE users SET deleted = true WHERE id =?";
    public static final String SELECT_EMAIL = "SELECT * FROM users WHERE email = ? AND deleted = false";
    public static final String SELECT_FIRSTNAME = "SELECT * FROM users WHERE lastname = ? AND deleted = false";
    public static final String SELECT_ROLE = "SELECT * FROM roles WHERE  role_id = ?";

    private DbConnectUser dbConnectUser;
    private static final Logger logger = LogManager.getLogger("request on bd");

    public void setDbConnectUser(DbConnectUser dbConnectUser) {
        this.dbConnectUser = dbConnectUser;
    }

    @Override
    public List<User> getAllUser() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        int sexId, adressId, roleId;
        Statement statement = dbConnectUser.initDbConnectionUsers().createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_USERS);
        logger.debug("sql request on bd -->" + SELECT_USERS);
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("pass"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setTelNum(resultSet.getString("telnumber"));
            sexId = resultSet.getInt("sex_id");
            user.setSex(getSexUser(sexId));
            adressId = resultSet.getInt("adress_id");
            user.setAdress(getAdressUser(adressId));
            roleId = resultSet.getInt("role_id");
            user.setRole(getRoleUser(roleId));
            users.add(user);
        }
        return users;
    }

    private Sex getSexUser(int sexId) throws SQLException, ClassNotFoundException {
        String sexName = "";
        Sex sex = null;
        PreparedStatement statement = dbConnectUser.initDbConnectionUsers().prepareStatement(SELECT_SEX);
        logger.debug("sql request on bd -->" + SELECT_SEX);

        statement.setLong(1, sexId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            sexName = resultSet.getString("sex_name");
        }
        switch (sexName.toUpperCase()) {
            case ("MAN"):
                sex = Sex.MAN;
                break;
            default:
                sex = Sex.WOMAN;
                break;
        }

        return sex;
    }

    private Role getRoleUser(int roleId) throws SQLException, ClassNotFoundException {
        String roleName = "";
        Role role;
        PreparedStatement preparedStatement = dbConnectUser.initDbConnectionUsers().prepareStatement(SELECT_ROLE);
        logger.debug("sql request on bd -->" + SELECT_ROLE);

        preparedStatement.setInt(1, roleId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            roleName = resultSet.getString("role_id");
        }
        switch (roleName.toUpperCase()) {
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

    private Adress getAdressUser(long adressId) throws SQLException, ClassNotFoundException {
        Adress adress = new Adress();

        PreparedStatement statement = dbConnectUser.initDbConnectionUsers().prepareStatement(SELECT_ADRESS_ID);
        logger.debug("sql request on bd -->" + SELECT_ADRESS_ID);
        statement.setLong(1, adressId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            adress.setCountry(resultSet.getString("country"));
            adress.setCity(resultSet.getString("city"));
            adress.setStreet(resultSet.getString("street"));
            adress.setStrNum(resultSet.getInt("strNum"));
            adress.setApart(resultSet.getInt("apart"));
        }

        return adress;
    }

    @Override
    public User getUserById(Long id) throws SQLException, ClassNotFoundException {
        int sexId, adressId, roleId;
        User user = new User();
        PreparedStatement statement = dbConnectUser.initDbConnectionUsers().prepareStatement(SELECT_ID);
        logger.debug("sql request on bd -->" + SELECT_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("pass"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setTelNum(resultSet.getString("telnumber"));
            sexId = resultSet.getInt("sex_id");
            user.setSex(getSexUser(sexId));
            adressId = resultSet.getInt("adress_id");
            user.setAdress(getAdressUser(adressId));
            roleId = resultSet.getInt("role_id");
            user.setRole(getRoleUser(roleId));
        } else {
            System.out.println("id is not found");
        }

        return user;
    }

    @Override
    public User createUser(User user) throws SQLException, ClassNotFoundException {
        int adressId = 0;
        ResultSet resultSet = getResultSetUserLogin(user.getLogin());
        if (resultSet.next()) {
            System.out.println("sorry, user with login in db or user deleted");
        } else {
            // Insert SQL adress
            extracted(user);
            // select SQL adress return max adress_id
            Statement statement = dbConnectUser.initDbConnectionUsers().createStatement();
            ResultSet resultSet1 = statement.executeQuery(SELECT_MAX_ADRESS_ID);
            logger.debug("sql request on bd -->" + SELECT_MAX_ADRESS_ID);
            if (resultSet1.next()) {
                adressId = resultSet1.getInt("max");
                System.out.println(adressId);
            }
            // insert new user
            PreparedStatement preparedStatement1 = dbConnectUser.initDbConnectionUsers().prepareStatement(CREATE_USER);
            logger.debug("sql request on bd -->" + CREATE_USER);
            preparedStatement1.setInt(1, roleId(user));
            preparedStatement1.setString(2, user.getLogin());
            preparedStatement1.setString(3, user.getEmail());
            preparedStatement1.setString(4, user.getPassword());
            preparedStatement1.setString(5, user.getFirstName());
            preparedStatement1.setString(6, user.getLastName());
            preparedStatement1.setInt(7, adressId);
            preparedStatement1.setString(8, user.getTelNum());
            preparedStatement1.setInt(9, sexId(user));
            preparedStatement1.execute();
            System.out.println("congratulation, user is create");
        }

        return user;
    }

    private ResultSet getResultSetUserLogin(String login) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = dbConnectUser.initDbConnectionUsers().prepareStatement(SELECT_LOGIN);
        logger.debug("sql request on bd -->" + SELECT_LOGIN);
        preparedStatement.setString(1, login);
        return preparedStatement.executeQuery();
    }

    private void extracted(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatementAdress = dbConnectUser.initDbConnectionUsers().prepareStatement(INSERT_ADRESS);
        preparedStatementAdress.setString(1, user.getAdress().getCountry());
        preparedStatementAdress.setString(2, user.getAdress().getCity());
        preparedStatementAdress.setString(3, user.getAdress().getStreet());
        preparedStatementAdress.setInt(4, user.getAdress().getStrNum());
        preparedStatementAdress.setInt(5, user.getAdress().getApart());
        preparedStatementAdress.execute();
    }

    private int sexId(User user) {
        int sexId = user.getSex() == "MAN" ? 1 : 2;
        return sexId;
    }

    private int roleId(User user) {
        int roleId;
        switch (user.getRole()) {
            case ("ADMIN"):
                roleId = 1;
                break;
            case ("MNGR"):
                roleId = 2;
                break;
            default:
                roleId = 3;
                break;

        }
        return roleId;
    }

    @Override
    public User updateUser(User user) throws SQLException, ClassNotFoundException {
        int adressId = 0;
        //checking for the presence of a user's login
        ResultSet resultSet = getResultSetUserLogin(user.getLogin());
        if (resultSet.next()) {
            PreparedStatement preparedStatementAdress = dbConnectUser.initDbConnectionUsers().prepareStatement(SELECT_ADRESS_ID_2);
            logger.debug("sql request on bd -->" + SELECT_ADRESS_ID_2);
            preparedStatementAdress.setString(1, user.getLogin());
            ResultSet resultSet1 = preparedStatementAdress.executeQuery();
            if (resultSet1.next()) {
                adressId = resultSet1.getInt("adress_id");
            }
            PreparedStatement preparedStatementUpdateAdress = dbConnectUser.initDbConnectionUsers().prepareStatement(UPDATE_ADRESS);
            logger.debug("sql request on bd -->" + UPDATE_ADRESS);
            preparedStatementUpdateAdress.setString(1, user.getAdress().getCountry());
            preparedStatementUpdateAdress.setString(2, user.getAdress().getCity());
            preparedStatementUpdateAdress.setString(3, user.getAdress().getStreet());
            preparedStatementUpdateAdress.setInt(4, user.getAdress().getStrNum());
            preparedStatementUpdateAdress.setInt(5, user.getAdress().getApart());
            preparedStatementUpdateAdress.setInt(6, adressId);
            preparedStatementUpdateAdress.execute();

            PreparedStatement preparedStatement2 = dbConnectUser.initDbConnectionUsers().prepareStatement(UPDATE_USER);
            logger.debug("sql request on bd -->" + UPDATE_USER);
            preparedStatement2.setInt(1, roleId(user));
            preparedStatement2.setString(2, user.getPassword());
            preparedStatement2.setString(3, user.getFirstName());
            preparedStatement2.setString(4, user.getLastName());
            preparedStatement2.setString(5, user.getTelNum());
            preparedStatement2.setInt(6, sexId(user));
            preparedStatement2.setString(7, user.getLogin());
            preparedStatement2.execute();
            System.out.println("user update");
        } else {
            System.out.println("user is not found or deleted");
        }
        return user;
    }

    @Override
    public boolean deleteUser(Long id) throws SQLException, ClassNotFoundException {
        boolean statusOperation = false;

        PreparedStatement preparedStatement = dbConnectUser.initDbConnectionUsers().prepareStatement(SELECT_ID);
        logger.debug("sql request on bd -->" + SELECT_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            PreparedStatement preparedStatement1 = dbConnectUser.initDbConnectionUsers().prepareStatement(UPDATE_USERS_DELETE);
            logger.debug("sql request on bd -->" + UPDATE_USERS_DELETE);
            preparedStatement1.setLong(1, id);
            preparedStatement1.executeUpdate();
            System.out.println("congratulation, user is deleted");
            statusOperation = true;
        } else {
            System.out.println("user with ID not found");
        }

        return statusOperation;
    }

    @Override
    public User getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        int sexId, adressId, roleId;
        User user = new User();
        PreparedStatement preparedStatement = dbConnectUser.initDbConnectionUsers().prepareStatement(SELECT_EMAIL);
        logger.debug("sql request on bd -->" + SELECT_EMAIL);
        preparedStatement.setString(1, email.toLowerCase());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("pass"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setTelNum(resultSet.getString("telnumber"));
            sexId = resultSet.getInt("sex_id");
            user.setSex(getSexUser(sexId));
            adressId = resultSet.getInt("adress_id");
            System.out.println(adressId);
            user.setAdress(getAdressUser(adressId));
            roleId = resultSet.getInt("role_id");
            user.setRole(getRoleUser(roleId));
        } else {
            System.out.println("user with email not found");
        }

        return user;
    }

    @Override
    public List<User> getUsersByLastName(String lastName) throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        int sexId, adressId, roleId;
        PreparedStatement preparedStatement = dbConnectUser.initDbConnectionUsers().prepareStatement(SELECT_FIRSTNAME);
        logger.debug("sql request on bd -->" + SELECT_FIRSTNAME);
        preparedStatement.setString(1, lastName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("pass"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setTelNum(resultSet.getString("telnumber"));
            sexId = resultSet.getInt("sex_id");
            user.setSex(getSexUser(sexId));
            adressId = resultSet.getInt("adress_id");
            user.setAdress(getAdressUser(adressId));
            roleId = resultSet.getInt("role_id");
            user.setRole(getRoleUser(roleId));
            users.add(user);
        }
        if (users.isEmpty()) {
            System.out.println("user(s) with lastname's is not found");
        }
        return users;
    }

    @Override
    public int countAllUsers() throws SQLException, ClassNotFoundException {
        int count = 0;
        List<User> users = getAllUser();
        if (!users.isEmpty()) {
            count = users.size();
        }
        return count;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException, ClassNotFoundException {
        int sexId, adressId, roleId;
        User user = new User();
        PreparedStatement preparedStatement = dbConnectUser.initDbConnectionUsers().prepareStatement(SELECT_LOGIN);
        logger.debug("sql request on bd -->" + SELECT_LOGIN);
        preparedStatement.setString(1, login.toLowerCase());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("pass"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setTelNum(resultSet.getString("telnumber"));
            sexId = resultSet.getInt("sex_id");
            user.setSex(getSexUser(sexId));
            adressId = resultSet.getInt("adress_id");
            System.out.println(adressId);
            user.setAdress(getAdressUser(adressId));
            roleId = resultSet.getInt("role_id");
            user.setRole(getRoleUser(roleId));
        } else {
            System.out.println("user with email not found");
        }

        return user;
    }
}
