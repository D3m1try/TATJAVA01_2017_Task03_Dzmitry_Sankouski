package com.epam.news_manager.services.impl;

import com.epam.news_manager.bean.Beans;
import com.epam.news_manager.services.Catalog;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 03-Feb-17.
 */
public abstract class AbstractCatalog<T extends Beans> implements Catalog<Beans>{
    @Override
    public void add(String request) {

    }

    @Override
    public void delete() {

    }

    @Override
    public List<Beans> find(String request) {
        return null;
    }

    @Override
    public void save() {

    }
}
