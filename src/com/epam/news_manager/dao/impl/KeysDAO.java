package com.epam.news_manager.dao.impl;

import com.epam.news_manager.bean.Keys;
import com.epam.news_manager.dao.exception.DAOException;

import java.io.*;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class KeysDAO extends FileGenericDAOImpl<Keys> {

    public Keys read() throws DAOException {
        FileInputStream fis = null;
        ObjectInputStream oin = null;
        Keys result = null;

        try {
            fis = new FileInputStream(new File(Keys.selfId));
        } catch (FileNotFoundException e) {
            throw new DAOException();
        }
        try {
            oin = new ObjectInputStream(fis);
            result = (Keys) oin.readObject();
        } catch (IOException e) {
            System.out.println("IO Exception while reading " + FileIdGenerator.getInstance().generateId(result));
        } catch (ClassNotFoundException e) {
            System.out.println("Class \"Shop\" not found.");
        }//TODO handle exceptions
        finally {
            try {
                oin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
