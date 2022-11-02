package com.romashkevich.store.controller.command.commandImpl.book;

import com.romashkevich.store.books.service.ServiceBook;
import com.romashkevich.store.books.service.ServiceBookImpl;
import com.romashkevich.store.books.service.dto.BookDto;
import com.romashkevich.store.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("books")
public class BooksCommand implements Command {
    private ServiceBookImpl SERVICE_BOOK_AppAll;
@Autowired
    public void setSERVICE_BOOK_AppAll(ServiceBookImpl SERVICE_BOOK_AppAll) {
        this.SERVICE_BOOK_AppAll = SERVICE_BOOK_AppAll;
    }

    public String execute(HttpServletRequest req){
            try {
                List<BookDto> bookDtos = new ArrayList<>(SERVICE_BOOK_AppAll.getAllBooksDto());
                if (bookDtos.isEmpty()){
                    throw new Exception();
                }
                req.setAttribute("books", bookDtos);
                return "jsp/books.jsp";
            } catch (Exception e) {
                return "jsp/error.jsp";
            }

    }
}