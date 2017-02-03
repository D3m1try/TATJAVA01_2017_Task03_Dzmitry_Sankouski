package com.epam.news_manager.controller.commands;

import com.epam.news_manager.controller.Command;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class Find implements Command {
    @Override
    public String execute(String request) {
        System.out.println("find");
        return "find";
    }
}
