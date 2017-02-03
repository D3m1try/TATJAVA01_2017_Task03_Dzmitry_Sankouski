package com.epam.news_manager.services;


import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 30-Jan-17.
 */
public interface Catalog<T> {

    public void add(String request);

    public void delete();

    public List<T> find(String request);

    public void save();
}
