package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.domains.Inheritance.TablePerClassStrategy.Cat;
import com.oonurkuru.jpa.domains.Inheritance.TablePerClassStrategy.Dog;

import javax.persistence.EntityManager;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * JPA Inheritance özelliği, Table Per Class Strateji için oluşturulmuş test kodu.
 */
public class TablePerClassExample implements Example {

    public void runExample(EntityManager entityManager) {
        Cat cat = new Cat("Kuru", 4, false, true);
        Dog dog = new Dog("Kuru", 4, false, true);

        entityManager.persist(cat);
        entityManager.persist(dog);
    }
}
