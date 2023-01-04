package com.romashkevich.store.books.dao;


import com.romashkevich.store.books.dao.entity.Book;
import com.romashkevich.store.books.dbServiceBook.DbConnectBook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//main.books.dao - data access object / / объект доступа к данным
@Repository
public class BookDaoJdbcImpl implements Bookdao {
    private DbConnectBook dbConnectBook;
    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper rowMapper;

    public static final String SELECT_ISBN = "SELECT * FROM books WHERE isbn =? AND deleted = false";
    public static final String GET_ALL = "SELECT * FROM books WHERE deleted = false";
    public static final String CREATE_BOOK = "INSERT INTO books (isbn, title, author, pages, cover, price) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_BOOK = "UPDATE books SET title = ?, author=?, pages=?, cover=?, price=? WHERE isbn=?";
    public static final String SELECT_BY_ID = "SELECT * FROM books WHERE id =? AND deleted = false";
    public static final String DELETE_BY_ID = "UPDATE books SET deleted = true WHERE id =?";
    public static final String GET_BY_ISBN = "SELECT * FROM books WHERE isbn =? AND deleted = false";
    public static final String SELECT_COUNT = "SELECT count(id) FROM books WHERE deleted=false";

    public static final Logger logger = LogManager.getLogger("request on bd");

    @Autowired
    public BookDaoJdbcImpl(JdbcTemplate jdbcTemplate, BookRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public void setDbConnectBook(DbConnectBook dbConnectBook) {
        this.dbConnectBook = dbConnectBook;
    }

    @Override
    public List<Book> getAllBooks(){
        return jdbcTemplate.query(GET_ALL, rowMapper);
    }

    @Override
    public Book getBookById(Long id) {
        return (Book) jdbcTemplate.queryForObject(SELECT_BY_ID, rowMapper, id);
    }

    @Override
    public Book createBook(Book book) throws SQLException {
        logger.debug("sql request on bd -->" + SELECT_ISBN);
        Book book1 = (Book) jdbcTemplate.queryForObject(SELECT_ISBN, rowMapper, book.getIsbn());
        if (book1 == null) {
            throw new SQLException("sorry, book with isbn in db");
        } else {
            jdbcTemplate.update(CREATE_BOOK, book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPages(), book.getCover(), book.getPrice());
        }
        return book;
    }

//        PreparedStatement statement = dbConnectBook.getConnection().prepareStatement(SELECT_ISBN);
//        logger.debug("sql request on bd -->" + SELECT_ISBN);
//        statement.setString(1, book.getIsbn());
//        ResultSet resultSet = statement.executeQuery();
//        if (resultSet.next()) {
//            throw new SQLException("sorry, book with isbn in db");
//        } else {
//            PreparedStatement preparedStatement = dbConnectBook.getConnection().prepareStatement(CREATE_BOOK);
//            logger.debug("sql request on bd -->" + CREATE_BOOK);
//            preparedStatement.setString(1, book.getIsbn());
//            preparedStatement.setString(2, book.getTitle());
//            preparedStatement.setString(3, book.getAuthor());
//            preparedStatement.setInt(4, book.getPages());
//            preparedStatement.setString(5, book.getCover());
//            preparedStatement.setBigDecimal(6, book.getPrice());
//            preparedStatement.executeUpdate();
//            System.out.println("congratulation, book is create");
//        }


    @Override
    public Book updateBook(Book book) {
        Book book1 = (Book) jdbcTemplate.queryForObject(SELECT_ISBN, rowMapper, book.getIsbn());
        logger.debug("sql request on bd -->" + SELECT_ISBN);
        if (book1 != null) {
            jdbcTemplate.update(UPDATE_BOOK, book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPages(), book.getCover(), book.getPrice());
        } else {
            System.out.println("book is not found");
        }
//        PreparedStatement statement = dbConnectBook.getConnection().prepareStatement(SELECT_ISBN);
//        logger.debug("sql request on bd -->" + SELECT_ISBN);
//        statement.setString(1, book.getIsbn());
//        ResultSet resultSet = statement.executeQuery();
//        if (resultSet.next()) {
//            PreparedStatement preparedStatement = dbConnectBook.getConnection().prepareStatement(UPDATE_BOOK);
//            logger.debug("sql request on bd -->" + UPDATE_BOOK);
//
//            preparedStatement.setString(1, book.getTitle());
//            preparedStatement.setString(2, book.getAuthor());
//            preparedStatement.setInt(3, book.getPages());
//            preparedStatement.setString(4, book.getCover());
//            preparedStatement.setBigDecimal(5, book.getPrice());
//            preparedStatement.setString(6, book.getIsbn());
//            preparedStatement.executeUpdate();
//            System.out.println("congratulation, book is update");
//            return book;
//        } else {
//            System.out.println("book is not found");
//        }

        return book;
    }

    @Override
    public boolean deleteBook(Long id) throws SQLException, ClassNotFoundException {

        boolean statusOperation = false;
        Book book = (Book) jdbcTemplate.queryForObject(SELECT_BY_ID, rowMapper, id);
        if (book != null) {
            logger.debug("sql request on bd -->" + DELETE_BY_ID);
            jdbcTemplate.update(DELETE_BY_ID, id);
            System.out.println("congratulation, book is deleted");
            statusOperation = true;

//        }
//        PreparedStatement statement = dbConnectBook.getConnection().prepareStatement(SELECT_BY_ID);
//        logger.debug("sql request on bd -->" + SELECT_BY_ID);
//        statement.setLong(1, id);
//        if (statement.executeQuery().next()) {
//            PreparedStatement preparedStatement = dbConnectBook.getConnection().prepareStatement(DELETE_BY_ID);
//            logger.debug("sql request on bd -->" + DELETE_BY_ID);
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//            System.out.println("congratulation, book is deleted");
//            statusOperation = true;

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
            book = (Book) jdbcTemplate.queryForObject(GET_BY_ISBN, rowMapper, isbn);
            if (book != null) {
                return book;
            }
        } else {
            System.out.println("isbn is not correct");
        }
        return book;
//            PreparedStatement statement = dbConnectBook.getConnection().prepareStatement(GET_BY_ISBN);
//            logger.debug("sql request on bd -->" + GET_BY_ISBN);
//            statement.setString(1, isbn);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                book = new Book();
//                book.setId(resultSet.getLong("id"));
//                book.setIsbn(resultSet.getString("isbn"));
//                book.setTitle(resultSet.getString("title"));
//                book.setAuthor(resultSet.getString("author"));
//                book.setPages(resultSet.getInt("pages"));
//                book.setCover(resultSet.getString("cover"));
//                book.setPrice(resultSet.getBigDecimal("price"));
//                return book;
//            }

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
