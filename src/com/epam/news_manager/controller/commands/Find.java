package com.epam.news_manager.controller.commands;

import com.epam.news_manager.controller.Command;
import com.epam.news_manager.services.impl.BooksCatalog;
import com.epam.news_manager.services.impl.DisksCatalog;
import com.epam.news_manager.services.impl.MoviesCatalog;
import com.epam.news_manager.view.Viewer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class Find implements Command {
    @Override
    public String execute(String request) {
        Pattern pattern = Pattern.compile("^(\\s*)(\\w+)(.+)");
        Matcher matcher = pattern.matcher(request);
        matcher.find();
        if (matcher.group(2).toLowerCase().equals("book")){
            return Viewer.beansToString(BooksCatalog.getInstance().find(matcher.group(3)));
        }
        if (matcher.group(2).toLowerCase().equals("disk")){
            return Viewer.beansToString(DisksCatalog.getInstance().find(matcher.group(3)));
        }
        if (matcher.group(2).toLowerCase().equals("movie")){
            return Viewer.beansToString(MoviesCatalog.getInstance().find(matcher.group(3)));
        }

        return "find";
    }
}
