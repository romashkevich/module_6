package books.dao;

import books.dao.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface Bookdao {

    List<Book> getAllBooks() throws SQLException, ClassNotFoundException;

    Book getBookById(Long id) throws SQLException, ClassNotFoundException;

    Book createBook(Book book) throws Exception; // передаем книгу в базу данных и создаем строку с ее данными

    Book updateBook(Book book) throws SQLException, ClassNotFoundException;// замена инфы передаваемой книги и возврат измененных значений

    boolean deleteBook(Long id) throws SQLException, ClassNotFoundException;// передача в бд книги ее поиск и дальнейшая пометка удалена

    Book getBookByIsbn(String isbn) throws SQLException, ClassNotFoundException;

    List<Book> getBookByAuthor(String author) throws SQLException, ClassNotFoundException;

    int countAllBooks() throws SQLException, ClassNotFoundException;
}
