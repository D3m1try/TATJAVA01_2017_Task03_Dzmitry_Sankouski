package com.epam.news_manager.services.impl;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Book;
import com.epam.news_manager.bean.Movie;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.impl.DAOFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class MoviesCatalog implements com.epam.news_manager.services.Catalog<Movie> {
    private static MoviesCatalog instance = new MoviesCatalog();
    private boolean isBooksUp = false;
    private Set<Movie> movies = new HashSet<>();
    private Set<Movie> savedMovies;

    private MoviesCatalog(){

    }

    private void initBooks(){
        if (BeanFactory.getInstance().getKeys().getBookIDs().size() == 0){
            savedMovies = new HashSet<>();
        } else {
            for (String id:
                    BeanFactory.getInstance().getKeys().getBookIDs()) {
                try {
                    savedMovies.add(DAOFactory.getInstance().getMovieDAO().read(id));
                } catch (DAOException e) {
                    e.printStackTrace();//TODO throw exception
                }
            }
        }
    }

    @Override
    public void add(String request) {
        Movie newMovie = new Movie();
        fillMovie(newMovie,request);

        movies.add(newMovie);
        BeanFactory.getInstance().getKeys().getMovieIDs().add(
                DAOFactory.getInstance().getMovieDAO().create(newMovie));
        DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
    }

    public void edit(String request) {

    }

    public void fillMovie(Movie movie, String request){
        Pattern pattern = Pattern.compile("\\s(\\-[^\\s]+)\\s+([^\\s]+)");
        Matcher matcher = pattern.matcher(request);

        while (matcher.find()){
            if (matcher.group(1).toUpperCase().equals("-TH")){
                movie.setTheme(matcher.group(2));
            } if (matcher.group(1).toUpperCase().equals("-T")){
                movie.setTitle(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("D")){
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    movie.setDate(format.parse(matcher.group(2)));
                } catch (ParseException e) {
                    e.printStackTrace();//TODO throw exception
                }
            }

            if (matcher.group(1).toUpperCase().contains("M")){
                movie.setMessage(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("S")){
                movie.setSlogan(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("L")){
                try {
                    movie.setLength(Integer.valueOf(matcher.group(2)));
                } catch (NumberFormatException e) {
                    e.printStackTrace();// TODO throw exception
                }
            }
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public List<Movie> find(String request) {
        Pattern pattern = Pattern.compile("(\\-p)?\\s?(\\w+)\\s+(.+)");
        Matcher matcher = pattern.matcher(request);

        if (!matcher.find()){
            // TODO illegal arguments exception
        }
        if (matcher.group(1) != null) {
            return DAOFactory.getInstance().getMovieDAO().find(matcher.group(2), matcher.group(3), true);
        } else {
            return DAOFactory.getInstance().getMovieDAO().find(matcher.group(2), matcher.group(3), false);
        }
    }

    @Override
    public void save() {
        for (Movie movie:
                movies) {
            DAOFactory.getInstance().getMovieDAO().create(movie);
            savedMovies.add(movie);
        }

        DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
        DAOFactory.getInstance().getMovieDAO().update(BeanFactory.getInstance().getMovie());

    }

    public static MoviesCatalog getInstance() {
        return instance;
    }
}
