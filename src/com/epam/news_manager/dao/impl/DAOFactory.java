package com.epam.news_manager.dao.impl;

import com.epam.news_manager.bean.*;

/**
 * Created by Dzmitry_Sankouski on 30-Jan-17.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private KeysDAO keysDAO = new KeysDAO();
    private FileGenericDAOImpl<Book> bookDAO = new FileGenericDAOImpl<>();
    private FileGenericDAOImpl<Disk> diskDAO = new FileGenericDAOImpl<Disk>();
    private FileGenericDAOImpl<Movie> movieDAO = new FileGenericDAOImpl<Movie>();
    private FileGenericDAOImpl<Books> booksDAO = new FileGenericDAOImpl<Books>();
    private FileGenericDAOImpl<Disks> disksDAO = new FileGenericDAOImpl<Disks>();
    private FileGenericDAOImpl<Movies> moviesDAO = new FileGenericDAOImpl<Movies>();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public KeysDAO getKeysDAO() {
        return keysDAO;
    }

    public FileGenericDAOImpl<Book> getBookDAO() {
        return bookDAO;
    }

    public FileGenericDAOImpl<Disk> getDiskDAO() {
        return diskDAO;
    }

    public FileGenericDAOImpl<Movie> getMovieDAO() {
        return movieDAO;
    }

    public FileGenericDAOImpl<Books> getBooksDAO() {
        return booksDAO;
    }

    public FileGenericDAOImpl<Disks> getDisksDAO() {
        return disksDAO;
    }

    public FileGenericDAOImpl<Movies> getMoviesDAO() {
        return moviesDAO;
    }
}
