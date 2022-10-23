package com.romashkevich.store.controller.command;

import com.romashkevich.store.controller.command.commandImpl.*;
import com.romashkevich.store.controller.command.commandImpl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
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
        map.put("com/romashkevich/store/books", new BooksCommand());
        map.put("user", new UserCommand());
        map.put("com/romashkevich/store/users", new UsersCommand());
        map.put("error", new ErrorCommand());
        map.put("create", new CreateCommand());
        map.put("delete", new DeleteCommand());
    }

    public Command getCommand(String action) {
       Command command =  map.get(action);
        if (command == null) {
            return map.get("error");
        }
        return command;

    }
}