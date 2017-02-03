package com.epam.news_manager.controller;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public interface Command {
    public String execute(String request);
}
