package books.service;

import books.service.dto.BookDto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface ServiceBook {

    Collection<? extends BookDto> getAllBooksDto() throws SQLException, ClassNotFoundException;

    BookDto getBookDtoById(Long id) throws SQLException, ClassNotFoundException;

    BookDto createBookDto(BookDto bookDto) throws Exception; // передаем книгу в базу данных и создаем строку с ее данными

    BookDto updateBookDto(BookDto bookDto) throws SQLException, ClassNotFoundException;// замена инфы передаваемой книги и возврат измененных значений

    Boolean deleteBookDto(Long id) throws SQLException, ClassNotFoundException;// передача в бд книги ее поиск и дальнейшая пометка удалена

    BookDto getBookDtoByIsbn(String isbn) throws SQLException, ClassNotFoundException;

    List<BookDto> getBookDtoByAuthor(String author) throws SQLException, ClassNotFoundException;

    int countAllBookDto() throws SQLException, ClassNotFoundException;

    BigDecimal priceBookDtoByAuthors(String author) throws SQLException, ClassNotFoundException;
}
