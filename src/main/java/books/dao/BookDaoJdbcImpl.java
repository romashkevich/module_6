package books.dao;


import books.dao.entity.Book;

import books.dbServiceBook.DbConfiguratorBooks;
import books.dbServiceBook.DbConnectBook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//main.books.dao - data access object / / объект доступа к данным

public class BookDaoJdbcImpl implements Bookdao {
    private DbConnectBook dbConnectBook;

    public static final String SELECT_ISBN = "SELECT * FROM books WHERE isbn =? AND deleted = false";
    public static final String GET_ALL = "SELECT * FROM books WHERE deleted = false";
    public static final String SELECT_GET_ID = "SELECT * FROM books WHERE id =? AND deleted = false";
    public static final String CREATE_BOOK = "INSERT INTO books (isbn, title, author, pages, cover, price) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_BOOK = "UPDATE books SET title = ?, author=?, pages=?, cover=?, price=? WHERE isbn=?";
    public static final String SELECT_BY_ID = "SELECT * FROM books WHERE id =? AND deleted = false";
    public static final String DELETE_BY_ID = "UPDATE books SET deleted = true WHERE id =?";
    public static final String GET_BY_ISBN = "SELECT * FROM books WHERE isbn =? AND deleted = false";
    public static final String SELECT_COUNT = "SELECT count(id) FROM books WHERE deleted=false";

    public static final Logger logger = LogManager.getLogger("request on bd");

    public BookDaoJdbcImpl(DbConnectBook dbConnectBook) {
        this.dbConnectBook = dbConnectBook;
    }

    public void setDbConnectBook(DbConnectBook dbConnectBook) {
        this.dbConnectBook = dbConnectBook;
    }

    @Override
    public List<Book> getAllBooks() throws SQLException, ClassNotFoundException {

        List<Book> books = new ArrayList<>();
        Connection connection = dbConnectBook.getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(GET_ALL);
        logger.debug("sql request on bd -->" + GET_ALL);
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setPages(resultSet.getInt("pages"));
            book.setCover(resultSet.getString("cover"));
            book.setPrice(resultSet.getBigDecimal("price"));
            books.add(book);
        }
        return books;
    }

    @Override
    public Book getBookById(Long id) throws SQLException, ClassNotFoundException {
        Book book = new Book();
        PreparedStatement statement = dbConnectBook.getConnection().prepareStatement(SELECT_GET_ID);
        logger.debug("sql request on bd -->" + SELECT_GET_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setPages(resultSet.getInt("pages"));
            book.setCover(resultSet.getString("cover"));
            book.setPrice(resultSet.getBigDecimal("price"));
            return book;
        }

        return book;
    }

    @Override
    public Book createBook(Book book) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = dbConnectBook.getConnection().prepareStatement(SELECT_ISBN);
        logger.debug("sql request on bd -->" + SELECT_ISBN);
        statement.setString(1, book.getIsbn());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            throw new SQLException("sorry, book with isbn in db");
        } else {
            PreparedStatement preparedStatement = DbConfiguratorBooks.getConnectionBooks().prepareStatement(CREATE_BOOK);
            logger.debug("sql request on bd -->" + CREATE_BOOK);
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getPages());
            preparedStatement.setString(5, book.getCover());
            preparedStatement.setBigDecimal(6, book.getPrice());
            preparedStatement.executeUpdate();
            System.out.println("congratulation, book is create");
        }

        return book;
    }

    @Override
    public Book updateBook(Book book) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = dbConnectBook.getConnection().prepareStatement(SELECT_ISBN);
        logger.debug("sql request on bd -->" + SELECT_ISBN);
        statement.setString(1, book.getIsbn());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            PreparedStatement preparedStatement = DbConfiguratorBooks.getConnectionBooks().prepareStatement(UPDATE_BOOK);
            logger.debug("sql request on bd -->" + UPDATE_BOOK);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPages());
            preparedStatement.setString(4, book.getCover());
            preparedStatement.setBigDecimal(5, book.getPrice());
            preparedStatement.setString(6, book.getIsbn());
            preparedStatement.executeUpdate();
            System.out.println("congratulation, book is update");
            return book;
        } else {
            System.out.println("book is not found");
        }

        return book;
    }

    @Override
    public boolean deleteBook(Long id) throws SQLException, ClassNotFoundException {

        boolean statusOperation = false;
        PreparedStatement statement = dbConnectBook.getConnection().prepareStatement(SELECT_BY_ID);
        logger.debug("sql request on bd -->" + SELECT_BY_ID);
        statement.setLong(1, id);
        if (statement.executeQuery().next()) {
            PreparedStatement preparedStatement = DbConfiguratorBooks.getConnectionBooks().prepareStatement(DELETE_BY_ID);
            logger.debug("sql request on bd -->" + DELETE_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("congratulation, book is deleted");
            statusOperation = true;

        } else {
            System.out.println("book is not found");
        }

        return statusOperation;
    }

    @Override
    public Book getBookByIsbn(String isbn) throws SQLException, ClassNotFoundException {
        boolean result = isbn.matches("\\d{4}[-]{1}\\d{4}");
        Book book = new Book();
        if (result) {
            PreparedStatement statement = dbConnectBook.getConnection().prepareStatement(GET_BY_ISBN);
            logger.debug("sql request on bd -->" + GET_BY_ISBN);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPages(resultSet.getInt("pages"));
                book.setCover(resultSet.getString("cover"));
                book.setPrice(resultSet.getBigDecimal("price"));
                return book;
            }
        } else {
            System.out.println("isbn is not correct");
        }
        return book;
    }

    @Override
    public List<Book> getBookByAuthor(String author) throws SQLException, ClassNotFoundException {
        List<Book> books = getAllBooks();
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : books) {
            String input = book.getAuthor().toLowerCase();// строка в которой ищут
            Pattern pattern = Pattern.compile(author.toLowerCase() + "\\s*\\w*"); // поиск совпадений
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                booksByAuthor.add(book);
                System.out.println(book);
            }
        }
        return booksByAuthor;
    }

    @Override
    public int countAllBooks() throws SQLException, ClassNotFoundException {
        int count = 0;
        Statement statement = dbConnectBook.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_COUNT);
        logger.debug("sql request on bd -->" + SELECT_COUNT);
        if (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        return count;
    }
}
