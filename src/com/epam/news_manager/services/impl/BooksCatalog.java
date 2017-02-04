package com.epam.news_manager.services.impl;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Book;
import com.epam.news_manager.bean.Keys;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.impl.DAOFactory;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class BooksCatalog implements com.epam.news_manager.services.Catalog<Book> {
    private static BooksCatalog instance = new BooksCatalog();
    private boolean isBooksUp = false;
    private Set<Book> books = new HashSet<>();
    private Set<Book> savedBooks;

    private BooksCatalog(){

    }

    private void initBooks(){
        if (BeanFactory.getInstance().getKeys().getBookIDs().size() == 0){
            savedBooks = new HashSet<>();
        } else {
            for (String id:
                    BeanFactory.getInstance().getKeys().getBookIDs()) {
                try {
                    savedBooks.add(DAOFactory.getInstance().getBookDAO().read(id));
                } catch (DAOException e) {
                    e.printStackTrace();//TODO throw exception
                }
            }
        }
    }

    @Override
    public void add(String request) {
        Book newBook = new Book();
        fillBook(newBook,request);

        books.add(newBook);
        BeanFactory.getInstance().getKeys().getBookIDs().add(
                DAOFactory.getInstance().getBookDAO().create(newBook));
    }

    public void edit(String request) {

    }

    public void fillBook(Book book, String request){
        Pattern pattern = Pattern.compile("(\\s\\-[^\\s]+\\s+)([^\\s]+)");
        Matcher matcher = pattern.matcher(request);

        while (matcher.find()){
            if (matcher.group(1).toUpperCase().contains("ISBN")){
                book.setISBN(matcher.group(2));
            } if (matcher.group(1).toUpperCase().contains("T")){
                book.setTitle(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("D")){
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    book.setDate(format.parse(matcher.group(2)));
                } catch (ParseException e) {
                    e.printStackTrace();//TODO throw exception
                }
            }

            if (matcher.group(1).toUpperCase().contains("M")){
                book.setMessage(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("P")){
                try {
                    book.setPageCount(Integer.valueOf(matcher.group(2)));
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
    public List<Book> find(String request) {
        return null;
    }

    @Override
    public void save() {
        for (Book book:
             books) {
            DAOFactory.getInstance().getBookDAO().create(book);
            savedBooks.add(book);
        }

        DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
        DAOFactory.getInstance().getBooksDAO().update(BeanFactory.getInstance().getBooks());

    }

    public static BooksCatalog getInstance() {
        return instance;
    }
}
