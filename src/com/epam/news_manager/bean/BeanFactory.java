package com.epam.news_manager.bean;

import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.impl.DAOFactory;

import java.security.PrivateKey;
import java.util.Date;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class BeanFactory {

    private Books books = new Books();
    private Disks disks = new Disks();
    private Movies movies = new Movies();
    private Keys keys;

    private static BeanFactory instance = new BeanFactory();

    private BeanFactory(){
        try {
            keys = DAOFactory.getInstance().getKeysDAO().read();
        } catch (DAOException e) {
            // TODO throw exception
        }finally {
            if (keys == null) {
                keys = new Keys();
            }
        }
    }

    //--------------getters


    public Keys getKeys() {
        return keys;
    }

    public Book getBook() {
        return new Book();
    }

    public Disk getDisk() {
        return new Disk();
    }

    public Movie getMovie() {
        return new Movie();
    }


    public Books getBooks() {
        return books;
    }

    public Disks getDisks() {
        return disks;
    }

    public Movies getMovies() {
        return movies;
    }




    public static BeanFactory getInstance() {
        return instance;
    }
}
