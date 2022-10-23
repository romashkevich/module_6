package com.romashkevich.store.books;

import com.romashkevich.store.books.dao.entity.Book;
import com.romashkevich.store.books.service.ServiceBook;
import com.romashkevich.store.books.service.ServiceBookImpl;
import com.romashkevich.store.books.service.dto.BookDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class AppBook {

    private static final Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ServiceBook serviceBook = context.getBean("serviceBook", ServiceBookImpl.class);

        boolean process = true;
        while (process) {
            System.out.println("\n\tconditon : all, id, create, update, delete, isbn, author, count or exit (for exit program)");
            Scanner in = new Scanner(System.in);
            String input = in.next().toLowerCase().trim();
            try {
                switch (input) {
                    case "all":
                        System.out.println("list books: ");
                        serviceBook.getAllBooksDto().forEach(System.out::println);
                        break;
                    case "id":
                        System.out.println("please input book id");
                        Scanner in1 = new Scanner(System.in);
                        Long id = in1.nextLong();
                        System.out.println("result");
                        System.out.println(serviceBook.getBookDtoById(id));
                        break;
                    case "create":
                        BookDto book = getBookDto();
                        serviceBook.createBookDto(book);
                        break;
                    case "update":
                        System.out.println("please input book isbn for update");
                        Scanner bookIsbn = new Scanner(System.in);
                        BookDto bookDto4Update = serviceBook.getBookDtoByIsbn(bookIsbn.nextLine());
                        serviceBook.updateBookDto(setBookDto(bookDto4Update));

                        break;
                    case "delete":
                        System.out.println("please input book id for delete");
                        Scanner in2 = new Scanner(System.in);
                        serviceBook.deleteBookDto(in2.nextLong());
                        break;
                    case "isbn":
                        System.out.println("please input isbn book(for example: 1234-1234)");
                        Scanner in3 = new Scanner(System.in);
                        BookDto book1 = serviceBook.getBookDtoByIsbn(in3.nextLine());
                        System.out.println(book1);
                        break;
                    case "author":
                        System.out.println("please input book author when you find");
                        Scanner in4 = new Scanner(System.in);
                        serviceBook.getBookDtoByAuthor(in4.next());

                        break;
                    case "count":
                        System.out.print("count books in db: ");
                        System.out.println(serviceBook.countAllBookDto());
                        break;
                    case "exit":
                        System.out.println("program is over");
                        process = false;
                        break;
                    default:
                        System.out.println("invalid command, try again");
                }
            } catch (Exception e) {
                logger.error(e);
            }

        }
        context.close();

    }

    private static Book getBook() {
        Book book = new Book();
        System.out.println("input book data");

        System.out.println("isbn(for example: 1234-1234 )");
        Scanner isbn = new Scanner(System.in);
        book.setIsbn(isbn.nextLine());

        System.out.println("title");
        Scanner title = new Scanner(System.in);
        book.setTitle(title.nextLine());

        System.out.println("author");
        Scanner author = new Scanner(System.in);
        book.setAuthor(author.nextLine());

        System.out.println("pages");
        Scanner pages = new Scanner(System.in);
        book.setPages(pages.nextInt());

        System.out.println("cover");
        Scanner cover = new Scanner(System.in);
        book.setCover(cover.next());

        System.out.println("price");
        Scanner price = new Scanner(System.in);
        book.setPrice(price.nextBigDecimal());
        return book;
    }

    private static Book setBookDto(Book book) {
        Book book1 = book;
        System.out.println("input book data");

        System.out.println("title");
        Scanner title = new Scanner(System.in);
        book1.setTitle(title.nextLine());

        System.out.println("author");
        Scanner author = new Scanner(System.in);
        book1.setAuthor(author.nextLine());

        System.out.println("pages");
        Scanner pages = new Scanner(System.in);
        book1.setPages(pages.nextInt());

        System.out.println("cover");
        Scanner cover = new Scanner(System.in);
        book1.setCover(cover.next());

        System.out.println("price");
        Scanner price = new Scanner(System.in);
        book1.setPrice(price.nextBigDecimal());
        return book1;
    }

    private static BookDto getBookDto() {
        BookDto book = new BookDto();
        System.out.println("input book data");

        System.out.println("isbn(for example: 1234-1234 )");
        Scanner isbn = new Scanner(System.in);
        book.setIsbn(isbn.nextLine());

        System.out.println("title");
        Scanner title = new Scanner(System.in);
        book.setTitle(title.nextLine());

        System.out.println("author");
        Scanner author = new Scanner(System.in);
        book.setAuthor(author.nextLine());

        System.out.println("pages");
        Scanner pages = new Scanner(System.in);
        book.setPages(pages.nextInt());

        System.out.println("cover");
        Scanner cover = new Scanner(System.in);
        book.setCover(cover.next());

        System.out.println("price");
        Scanner price = new Scanner(System.in);
        book.setPrice(price.nextBigDecimal());
        return book;
    }

    private static BookDto setBookDto(BookDto book) {
        BookDto book1 = book;
        System.out.println("input book data");

        System.out.println("title");
        Scanner title = new Scanner(System.in);
        book1.setTitle(title.nextLine());

        System.out.println("author");
        Scanner author = new Scanner(System.in);
        book1.setAuthor(author.nextLine());

        System.out.println("pages");
        Scanner pages = new Scanner(System.in);
        book1.setPages(pages.nextInt());

        System.out.println("cover");
        Scanner cover = new Scanner(System.in);
        book1.setCover(cover.next());

        System.out.println("price");
        Scanner price = new Scanner(System.in);
        book1.setPrice(price.nextBigDecimal());
        return book1;
    }

}



