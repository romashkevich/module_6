//package com.romashkevich.store.books;
//
//import com.romashkevich.store.books.dao.BookDaoJdbcImpl;
//import com.romashkevich.store.books.dbServiceBook.DbConnectBook;
//import com.romashkevich.store.books.service.ServiceBook;
//import com.romashkevich.store.books.service.ServiceBookImpl;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ComponentScan
//public class BookApplicationContexts {
//    @Bean
//    public DbConnectBook dbConnectBook(){
//        return new DbConnectBook();
//    }
//    @Bean
//    public BookDaoJdbcImpl bookDaoJdbc(){
//        BookDaoJdbcImpl bookDaoJdbc = new BookDaoJdbcImpl();
//        bookDaoJdbc.setDbConnectBook(dbConnectBook());
//        return bookDaoJdbc;
//    }
//
//    @Bean
//    public ServiceBook serviceBook() {
//        ServiceBookImpl bean = new ServiceBookImpl();
//        bean.setBookDao(bookDaoJdbc());
//        return bean;
//    }
//}
