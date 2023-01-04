//package com.romashkevich.store.controller;
//
////
//// enum map вложенный класс мап // патерны проектирования синглтон фабрика
////
//
//import com.romashkevich.store.controller.command.Command;
//import com.romashkevich.store.controller.command.commandImpl.*;
//import com.romashkevich.store.controller.command.commandImpl.book.*;
//import com.romashkevich.store.controller.command.commandImpl.user.UserCommand;
//import com.romashkevich.store.controller.command.commandImpl.user.UsersCommand;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.io.IOException;
//
//@WebServlet("/controller")
//public class Controller extends HttpServlet {
//    private ClassPathXmlApplicationContext context;
//
//    public void init() {
//        this.context = new ClassPathXmlApplicationContext("applicationContext.xml");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("command");
//        String page = ((Command) context.getBean(action)).execute(req);
//        req.getRequestDispatcher(page).forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("command");
//        String page = ((Command) context.getBean(action)).execute(req);
//        req.getRequestDispatcher(page).forward(req, resp);
//
//    }
//
//    @Override
//    public void destroy() {
//        super.destroy();
//        context.close();
//    }
//}
//
