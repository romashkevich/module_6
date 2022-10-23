package com.romashkevich.store.books.service;

import com.romashkevich.store.books.dao.BookDaoJdbcImpl;
import com.romashkevich.store.books.dao.entity.Book;
import com.romashkevich.store.books.service.dto.BookDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
//@Service("serviceBook")
public class ServiceBookImpl implements ServiceBook {
//    DbConnect dbConnect;
    private BookDaoJdbcImpl bookDao;
    private static final Logger loger = LogManager.getLogger("run main.books.service method");

//    @Autowired
    public void setBookDao(BookDaoJdbcImpl bookDao) {
        this.bookDao = bookDao;
    }


    @Override
    public Collection<? extends BookDto> getAllBooksDto() throws SQLException, ClassNotFoundException {
        loger.debug("");
        List<BookDto> bookDtos = bookDao.getAllBooks().stream()
                .map(entity -> toBookDto(entity))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public BookDto getBookDtoById(Long id) throws SQLException, ClassNotFoundException {
        loger.debug("");
        Book book = bookDao.getBookById(id);
        BookDto bookDto = toBookDto(book);
        return bookDto;
    }

    private BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());
        bookDto.setCover(book.getCover());
        bookDto.setPages(book.getPages());
        bookDto.setPrice(book.getPrice());
        return bookDto;
    }

    private Book toBook(BookDto bookDto) {
        Book book = new Book();
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setAuthor(bookDto.getAuthor());
        book.setPages(bookDto.getPages());
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setCover(bookDto.getCover());
        return book;
    }

    @Override
    public BookDto createBookDto(BookDto bookDto) throws Exception {
        loger.debug("");
        BookDto bDto;
        Book book = toBook(bookDto);
        Book bookExist = bookDao.createBook(book);
        bDto = toBookDto(bookExist);
        return bDto;
    }

    @Override
    public BookDto updateBookDto(BookDto bDto) throws SQLException, ClassNotFoundException {
        loger.debug("");
        BookDto bookDto;
        Book book = toBook(bDto);
        Book bookExist = bookDao.updateBook(book);
        bookDto = toBookDto(bookExist);
        return bookDto;
    }

    @Override
    public Boolean deleteBookDto(Long id) throws SQLException, ClassNotFoundException {
        loger.debug("");
        return bookDao.deleteBook(id);
    }

    @Override
    public BookDto getBookDtoByIsbn(String isbn) throws SQLException, ClassNotFoundException {
        loger.debug("");
        Book book = bookDao.getBookByIsbn(isbn);
        BookDto bookDto = toBookDto(book);
        return bookDto;
    }

    @Override
    public List<BookDto> getBookDtoByAuthor(String author) throws SQLException, ClassNotFoundException {
        loger.debug("");
        List<BookDto> bookDtos;
        List<Book> books = bookDao.getBookByAuthor(author);
        bookDtos = books.stream().map(this::toBookDto).collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public int countAllBookDto() throws SQLException, ClassNotFoundException {
        loger.debug("");
        int count = bookDao.countAllBooks();
        return count;
    }

    @Override
    public BigDecimal priceBookDtoByAuthors(String author) throws SQLException, ClassNotFoundException {
        loger.debug("");
        BigDecimal sumAll;
        double sum = 0;
        List<BookDto> books = getBookDtoByAuthor(author);
        if (!books.isEmpty()) {
            for (BookDto bDto : books) {
                sum = sum + bDto.getPrice().doubleValue();
            }
            sumAll = new BigDecimal(sum);
            System.out.println(sumAll);
        } else {
            throw new RuntimeException("Author is not found");
        }
        return sumAll;
    }
}
