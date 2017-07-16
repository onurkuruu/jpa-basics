package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.annotations.ExampleClassCode;
import com.oonurkuru.jpa.domains.Inheritance.JoinedTableStrategy.Cat;
import com.oonurkuru.jpa.domains.Inheritance.JoinedTableStrategy.Dog;

import javax.persistence.EntityManager;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * JPA Inheritance özelliği, Joined Table Strategy için hazırlanmış test kodu.
 */
@ExampleClassCode(classCode = "1")
public class JoinedTableExample implements Example {

    public void runExample(EntityManager entityManager) {
        Cat cat = new Cat("Onurr", 4, false, true);
        Dog dog = new Dog("Onurr", 4, false, true);

        entityManager.persist(cat);
        entityManager.persist(dog);
    }
}
