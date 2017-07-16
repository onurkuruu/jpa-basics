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
public abstract class ConnectionUtils {

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

    /**
     * Entity manager factory nesnesinin kapatılmasını sağlar.
     * @param entityManagerFactory nesnesinin bağlantısının kapatılmasını sağlar.
     */
    public static void closeEntityManagerFactory(EntityManagerFactory entityManagerFactory) {

        try {
            entityManagerFactory.close();
        } catch (Exception e) {
            System.out.println("EntityManagerFactory didn't close. " + e);
        }
    }

    /**
     * Entity Manager nesnesinin kapatılmasını sağlar
     * @param entityManager nesnesinin bağlantısının kapatılmasını sağlar.
     */
    public static void closeEntityManager(EntityManager entityManager) {
        try {
            entityManager.close();
        } catch (Exception e) {
            System.out.println("EntityManager didn't close. " + e);
        }
    }

}
