package com.epam.news_manager.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class Book extends Beans implements Serializable {
    private int pageCount;
    private String ISBN;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
