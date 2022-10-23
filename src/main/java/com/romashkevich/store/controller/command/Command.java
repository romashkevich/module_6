package com.romashkevich.store.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest req);
}
