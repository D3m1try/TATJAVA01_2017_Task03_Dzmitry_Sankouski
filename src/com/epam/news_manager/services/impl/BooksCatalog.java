package com.epam.news_manager.services.impl;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Book;
import com.epam.news_manager.dao.impl.DAOFactory;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class BooksCatalog implements com.epam.news_manager.services.Catalog<Book> {
    private static BooksCatalog instance = new BooksCatalog();

    private BooksCatalog(){

    }

    @Override
    public void add(String request) {
        Pattern pattern = Pattern.compile("(\\s\\-[^\\s]+\\s+)([^\\s]+)");
        Matcher matcher = pattern.matcher(request);

        Book newBook = new Book();
        while (matcher.find()){
            if (matcher.group(1).toUpperCase().contains("ISBN")){
                newBook.setISBN(matcher.group(2));
            } if (matcher.group(1).toUpperCase().contains("T")){
                newBook.setTitle(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("D")){
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    newBook.setDate(format.parse(matcher.group(2)));
                } catch (ParseException e) {
                    e.printStackTrace();//TODO throw exception
                }
            }

            if (matcher.group(1).toUpperCase().contains("M")){
                newBook.setMessage(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("P")){
                try {
                    newBook.setPageCount(Integer.valueOf(matcher.group(2)));
                } catch (NumberFormatException e) {
                    e.printStackTrace();// TODO throw exception
                }
            }
        }

        BeanFactory.getInstance().getBooks().getListOfBooks().add(newBook);
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
        DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
        DAOFactory.getInstance().getBooksDAO().update(BeanFactory.getInstance().getBooks());

    }

    public static BooksCatalog getInstance() {
        return instance;
    }
}
