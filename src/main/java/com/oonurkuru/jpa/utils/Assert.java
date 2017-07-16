package com.oonurkuru.jpa.utils;

/**
 * Created by Onur Kuru on 16.7.2017.
 */

abstract class Assert {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }
}
