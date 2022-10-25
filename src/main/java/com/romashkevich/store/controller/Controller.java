package com.romashkevich.store.controller;

//
// enum map вложенный класс мап // патерны проектирования синглтон фабрика
//

import com.romashkevich.store.controller.command.Command;
import com.romashkevich.store.controller.command.CommandFactory;
import com.romashkevich.store.controller.command.CommandFactoryCopy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private CommandFactoryCopy commandFactoryCopy;
    public void init(){
       ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
       CommandFactoryCopy cfc = ctx.getBean("command", CommandFactoryCopy.class);
       this.commandFactoryCopy = cfc;

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("command");
        Command command = commandFactoryCopy.getBookCommand(action);
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

