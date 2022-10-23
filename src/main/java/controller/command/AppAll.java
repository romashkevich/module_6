package controller.command;

import books.dao.BookDaoJdbcImpl;
import books.dbServiceBook.DbConnectBook;
import books.service.ServiceBook;
import books.service.ServiceBookImpl;
import books.service.dto.BookDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book")
public class AppAll extends HttpServlet {
    private static final ServiceBook SERVICE_BOOK_AppAll = new ServiceBookImpl(new BookDaoJdbcImpl(new DbConnectBook()));
    private static final Logger loggerAppAll = LogManager.getRootLogger();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setStatus(200);
        resp.setContentType("text/html");
        if (req.getParameter("id") == null) {
            try {
                List<BookDto> bookDtos = new ArrayList<>(SERVICE_BOOK_AppAll.getAllBooksDto());
                req.setAttribute("books", bookDtos);
                req.getRequestDispatcher("jsp/books.jsp").forward(req,resp);
            } catch (Exception e) {
                resp.sendError(404, "not connect with db");

            }
        }

        if (!(req.getParameter("id") == null)) {
            try {
                Long idValue = Long.parseLong(req.getParameter("id"));
                long count = (long) SERVICE_BOOK_AppAll.countAllBookDto();
                if (idValue >= 0 && idValue <= count) {
                    BookDto bookDto = SERVICE_BOOK_AppAll.getBookDtoById(idValue);
                    req.setAttribute("book",bookDto);
                    req.getRequestDispatcher("jsp/book.jsp").forward(req,resp);
                } else {
                    throw new SQLException();
                }

            } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
                resp.sendError(404, "this id is not correct or not connect with DB");
            }

        }

    }
}

