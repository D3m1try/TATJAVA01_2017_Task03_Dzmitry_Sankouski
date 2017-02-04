package com.epam.news_manager.view;

import com.epam.news_manager.bean.Beans;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 04-Feb-17.
 */
public class Viewer {

    public static String beansToString(List<? extends Beans> beans) {
        StringBuilder result = new StringBuilder();
        for (Beans bean :
                beans) {
            result.append(bean.getClass().getSimpleName()).append(":\n").
                append("Id: ").append(bean.getId()).append("\n").
                append("Title: ").append(bean.getTitle()).append("\n");
        }
        return result.toString();
    }
}
