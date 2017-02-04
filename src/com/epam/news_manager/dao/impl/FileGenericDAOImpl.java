package com.epam.news_manager.dao.impl;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Identifiable;
import com.epam.news_manager.dao.Findable;
import com.epam.news_manager.dao.GenericDAO;
import com.epam.news_manager.dao.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 30-Jan-17.
 */
public class FileGenericDAOImpl<T extends Serializable & Identifiable<String>> implements GenericDAO<T, String>,Findable<T> {
    private Class<T> type;


    public FileGenericDAOImpl() {

    }

    public FileGenericDAOImpl(Class<T> type) {
        this.type = type;
    }


    @Override
    public String create(T newInstance) {
        newInstance.setId(FileIdGenerator.getInstance().generateId(newInstance));
        this.update(newInstance);
        return newInstance.getId();
    }

    @Override
    public T read(String id) throws DAOException {
        FileInputStream fis = null;
        ObjectInputStream oin = null;
        T result = null;

        try {
            fis = new FileInputStream(new File(id));
        } catch (FileNotFoundException e) {
            throw new DAOException();
        }
        try {
            oin = new ObjectInputStream(fis);
            result = (T) oin.readObject();
        } catch (IOException e) {
            System.out.println("IO Exception while reading " + id);
        } catch (ClassNotFoundException e) {
            System.out.println("Class \"Shop\" not found.");
        } finally {
            try {
                oin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //TODO handle exceptions
        }
        return result;
    }


    @Override
    public void update(T transientObject) {
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(new File(String.valueOf(transientObject.getId())));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(transientObject);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println(transientObject.getId() + " not found");
        } catch (IOException e) {
            System.out.println("IO Exception while saving" + transientObject.getId());
        }
    }// TODO handle exceptions

    @Override
    public void delete(T persistentObject) {
        new File(String.valueOf(persistentObject.getId())).delete();
    }

    @Override
    public List<T> find(String fieldName, String value, boolean isPureSearch) {
        List<T> data = new ArrayList<>();
        for (String key:
                BeanFactory.getInstance().getKeys().getAll()) {
            if (key.contains(type.getSimpleName())){

                try {
                    data.add(this.read(key));
                } catch (DAOException e) {
                    e.printStackTrace(); //TODO Exception
                }
            }
        } // retrieving all data

        Pattern purePattern = Pattern.compile(fieldName + ":" + value + ";");
        Pattern ordinaryPattern = Pattern.compile(fieldName + ":" + ".*" + value + ".*");
        Matcher matcher;

        List<T> result = new ArrayList<>();
        for (T peaceOfData:
             data) {

            if (isPureSearch) {
                matcher = purePattern.matcher(peaceOfData.toString());
            } else {
                matcher = ordinaryPattern.matcher(peaceOfData.toString());
            }
            if (matcher.find()){
                result.add(peaceOfData);
            }
        }


        return result;
    }
}
