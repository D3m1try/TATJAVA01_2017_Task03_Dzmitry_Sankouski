package com.epam.news_manager.services.impl;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Disk;
import com.epam.news_manager.dao.impl.DAOFactory;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class DisksCatalog implements com.epam.news_manager.services.Catalog<Disk>{
    @Override
    public void add(String request) {
        Disk newDisk = new Disk();
        BeanFactory.getInstance().getDisks().getListOfDisks().add(newDisk);
    }

    @Override
    public void delete() {

    }

    @Override
    public List<Disk> find(String request) {
        return null;
    }

    @Override
    public void save() {
        DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());

        DAOFactory.getInstance().getDisksDAO().update(BeanFactory.getInstance().getDisks());
    }
}
