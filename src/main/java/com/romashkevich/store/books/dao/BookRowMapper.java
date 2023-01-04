package com.romashkevich.store.books.dao;

import com.romashkevich.store.books.dao.entity.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class BookRowMapper implements RowMapper  {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthor(rs.getString("author"));
        book.setTitle(rs.getString("title"));
        book.setPages(rs.getInt("pages"));
        book.setPrice(rs.getBigDecimal("price"));
        book.setCover(rs.getString("cover"));
        return book;
    }
}
