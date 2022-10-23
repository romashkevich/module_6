package com.romashkevich.store.controller;

//
// enum map вложенный класс мап // патерны проектирования синглтон фабрика
//

import com.romashkevich.store.controller.command.Command;
import com.romashkevich.store.controller.command.CommandFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/com/romashkevich/store/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("command");
        Command command = CommandFactory.getInstance().getCommand(action);
        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("command");
        Command command = CommandFactory.getInstance().getCommand(action);
        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req,resp);
        
    }
}

