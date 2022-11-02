package com.romashkevich.store.controller.command;

import com.romashkevich.store.controller.command.commandImpl.*;
import com.romashkevich.store.controller.command.commandImpl.book.BookCommand;
import com.romashkevich.store.controller.command.commandImpl.book.BooksCommand;
import com.romashkevich.store.controller.command.commandImpl.book.CreateBookCommand;
import com.romashkevich.store.controller.command.commandImpl.book.DeleteBookCommand;
import com.romashkevich.store.controller.command.commandImpl.user.UserCommand;
import com.romashkevich.store.controller.command.commandImpl.user.UsersCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private BookCommand bookCommand;
//класс холдерр
    private static class Holder{
        private static final CommandFactory instance = new CommandFactory();
    }
//конец класса холдер
    public static CommandFactory getInstance() {

        return Holder.instance;
    }

    private CommandFactory(){
    }
    private static final Map<String, Command> map = new HashMap<>();
    static {
        map.put("book", new BookCommand());
        map.put("books", new BooksCommand());
        map.put("user", new UserCommand());
        map.put("users", new UsersCommand());
        map.put("error", new ErrorCommand());
        map.put("create", new CreateBookCommand());
        map.put("delete", new DeleteBookCommand());
    }

    public Command getCommand(String action) {
       Command command =  map.get(action);
        if (command == null) {
            return map.get("error");
        }
        return command;

    }
}