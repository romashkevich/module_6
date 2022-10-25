package com.romashkevich.store.controller.command;

import com.romashkevich.store.controller.command.commandImpl.BookCommand;
import com.romashkevich.store.controller.command.commandImpl.ErrorCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("commandFactory")
public class CommandFactoryCopy {
    private BookCommand bookCommand;
    private ErrorCommand errorCommand;
    @Autowired
    public void setErrorCommand(ErrorCommand errorCommand) {
        this.errorCommand = errorCommand;
    }

    @Autowired
    public void setBookCommand(BookCommand bookCommand) {
        this.bookCommand = bookCommand;
    }


    public Command getBookCommand(String action) {
        if(action == "book"){
            return bookCommand;
        }
        return errorCommand;
    }
}
