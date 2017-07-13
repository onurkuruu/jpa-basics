package com.oonurkuru.jpa.utils;

/**
 * Created by Onur Kuru on 13.7.2017.
 */
public class ClassFinder {

    final static String TARGET_PACKAGE = "com.oonurkuru.jpa.examples";

    /**
     * TARGET_PACKAGE ve parametre olarak verilen className birleştirilerek ilgili sınıfın bulur
     *
     * @param className TARGET_PACKAGE altında bulunması istenen sınıfı belirtir.
     * @return eğer ilgili sınıf bulunursa geriye Class objesi döndürür.
     * @throws ClassNotFoundException geçerli bir sınıf ismi verilmediği takdir de bu hatayı fırlatır.
     */
    public static Class findClassByName(String className) throws ClassNotFoundException {
        return Class.forName(TARGET_PACKAGE + "." + className);
    }

}
