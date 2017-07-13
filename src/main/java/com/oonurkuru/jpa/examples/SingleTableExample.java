package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.domains.Inheritance.SingleTableStrategy.Cat;
import com.oonurkuru.jpa.domains.Inheritance.SingleTableStrategy.Dog;

import javax.persistence.EntityManager;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * JPA Inheritance özelliği, Single Table Strategy için hazırlanmış test kodu
 */
public class SingleTableExample implements Example {

    public void runExample(EntityManager entityManager) {
        Cat cat = new Cat("Onur", 4, false, true);
        Dog dog = new Dog("Onur", 4, false, true);

        entityManager.persist(cat);
        entityManager.persist(dog);
    }
}
