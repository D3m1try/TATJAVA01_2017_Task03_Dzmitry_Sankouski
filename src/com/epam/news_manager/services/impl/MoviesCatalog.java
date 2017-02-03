package com.epam.news_manager.services.impl;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Movie;
import com.epam.news_manager.dao.impl.DAOFactory;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class MoviesCatalog implements com.epam.news_manager.services.Catalog<Movie> {
    @Override
    public void add(String request) {
        Movie newMovie = new Movie();
        BeanFactory.getInstance().getMovies().getListOfMovies().add(newMovie);
    }

    @Override
    public void delete() {

    }

    @Override
    public List<Movie> find(String request) {
        return null;
    }

    @Override
    public void save() {
        DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
        DAOFactory.getInstance().getMoviesDAO().update(BeanFactory.getInstance().getMovies());
    }
}
