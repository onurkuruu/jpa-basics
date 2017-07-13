package com.oonurkuru.jpa;

import com.oonurkuru.jpa.examples.Example;
import com.oonurkuru.jpa.utils.ClassFinder;
import com.oonurkuru.jpa.utils.ConnectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by Onur Kuru on 13.7.2017.
 */
public class RunExample {

    private final static String PERSISTENCE_UNIT_NAME = "jpa_basics";

    public static void main(String args[]) {

        Example exampleClass = null;
        EntityManagerFactory entityManagerFactory;
        EntityManager entityManager;

        try {
            exampleClass = (Example) ClassFinder.findClassByName("TablePerClassExample").newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found : " + e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        if (exampleClass != null) {
            entityManagerFactory = ConnectionUtils.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            entityManager = ConnectionUtils.createEntityManager(entityManagerFactory);

            ConnectionUtils.startTransaction(entityManager);

            exampleClass.runExample(entityManager);

            ConnectionUtils.commitTransaction(entityManager);
            ConnectionUtils.closeConnections(entityManager);
        }
    }
}
