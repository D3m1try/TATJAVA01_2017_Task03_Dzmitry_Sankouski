package com.epam.news_manager.controller.impl;

import com.epam.news_manager.controller.Command;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public final class Controller {
    private final CommandProvider provider = new CommandProvider();
    private final char paramDelimeter = ' ';
    public String executeTask(String request){
        String commandName;
        Command executionCommand;
        commandName = request.substring(0, request.indexOf(paramDelimeter));
        request = request.substring(request.indexOf(paramDelimeter));
        executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(request);
        return response;
    }
}