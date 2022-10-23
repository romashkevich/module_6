package books.dbServiceBook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbConfiguratorBooks {
    private static Connection connection;
    private static final Logger loger = LogManager.getLogger("connect db");

    public static void initDbConnectionBooks() throws SQLException, ClassNotFoundException {  // метод подключения к базе данных
        String host = getUrl().get(0);
        String user = getUrl().get(1);
        String pass = getUrl().get(2);
        String url = getUrl().get(3);
        String local = getUrl().get(4);
        loger.info("data on connect db --> " + local);
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(local);
    }

    public static Connection getConnectionBooks() throws SQLException {
        if (connection == null) {
            try {
                initDbConnectionBooks();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static List<String> getUrl() {
        Properties properties = new Properties();
        List<String> inter = new ArrayList<String>();
        String local = "";
        String host = "";
        String user = "";
        String pass = "";
        String url = "";
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream foo = loader.getResourceAsStream("config.properties");
            properties.load(foo);
            host = properties.getProperty("db.host.remove.books.url");
            inter.add(host);
            user = properties.getProperty("db.host.remove.books.user");
            inter.add(user);
            pass = properties.getProperty("db.host.remove.books.password");
            inter.add(pass);
            url = properties.getProperty("db.host.remove.books.fullurl");
            inter.add(url);
            local = properties.getProperty("db.host.local.books");
            inter.add(local);
        } catch (IOException e) {
            loger.error("not connect with BD", e);
        }
        return inter;
    }
}
