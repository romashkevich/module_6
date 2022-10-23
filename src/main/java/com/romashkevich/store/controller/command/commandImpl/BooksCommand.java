package com.romashkevich.store.controller.command.commandImpl;

import com.romashkevich.store.books.service.ServiceBook;
import com.romashkevich.store.books.service.ServiceBookImpl;
import com.romashkevich.store.books.service.dto.BookDto;
import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class BooksCommand implements Command {
    private static final ServiceBook SERVICE_BOOK_AppAll = new ServiceBookImpl();

    public String execute(HttpServletRequest req){
            try {
                List<BookDto> bookDtos = new ArrayList<>(SERVICE_BOOK_AppAll.getAllBooksDto());
                if (bookDtos==null){
                    throw new Exception();
                }
                req.setAttribute("books", bookDtos);
                return "jsp/books.jsp";
            } catch (Exception e) {
                return "jsp/error.jsp";
            }

    }
}