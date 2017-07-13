package com.oonurkuru.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * JPA connectionlarını ve transactionları açıp kapatma fonksiyonlarından oluşur.
 */
public class ConnectionUtils {


    /**
     * Entity Manager Factory üretme fonksiyonu.
     *
     * @param persistenceUnitName verilen parametreye göre persistence.xml de tanımlı olan persistenceUnit getirilir.
     * @return geçerli bir persistenceUnit olduğu takdirde geriye EntityManagerFactory nesnesini döndürür.
     */
    public static EntityManagerFactory createEntityManagerFactory(String persistenceUnitName) {
        if (persistenceUnitName == null) {
            throw new NullPointerException("Persistence Unit Name Is Required.");
        }

        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }


    /**
     * EntityManager üretme fonksiyonu
     *
     * @param entityManagerFactory Verilen parametre kullanılarak bir EntityManager nesnesi üretir.
     * @return Üretilen EntityManager nesnesini geri döndürür.
     */
    public static EntityManager createEntityManager(EntityManagerFactory entityManagerFactory) {

        if (entityManagerFactory == null) {
            throw new NullPointerException("Entity Manager Factory Is Required");
        }

        return entityManagerFactory.createEntityManager();
    }

    /**
     * EntityManager ve EntityManagerFactory bağlantılarını kapatmayı sağlar.
     *
     * @param entityManager verilen parametre kullanılarak entitymManager ve entityManagerFactory nesnelerine ulaşılır
     *                      ve bağlantı kapatılır.
     */
    public static void closeConnections(EntityManager entityManager) {

        EntityManagerFactory entityManagerFactory;

        try {

            entityManagerFactory = entityManager.getEntityManagerFactory();
            entityManager.close();
            entityManagerFactory.close();

        } catch (Exception e) {
            System.out.println("An error occurred when connections closing: " + e);
        }

    }

    /**
     * @param entityManager için transaction başlatır.
     */
    public static void startTransaction(EntityManager entityManager) {

        try {
            entityManager.getTransaction().begin();
        } catch (Exception e) {
            System.out.println("Transaction failed to start: " + e);
        }
    }

    /**
     * @param entityManager için transactionı sonlandırır.
     */
    public static void commitTransaction(EntityManager entityManager) {

        try {
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Transaction failed to commit: " + e);
        }

    }

}
