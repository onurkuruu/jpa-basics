package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.annotations.ExampleClassCode;
import com.oonurkuru.jpa.domains.Relations.OneToOne.Person;
import com.oonurkuru.jpa.domains.Relations.OneToOne.Toothbrush;

import javax.persistence.EntityManager;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * JPA Relation özelliği, One To One relation için oluşturulmuş test kodu
 */

@ExampleClassCode(classCode = "4")
public class OneToOneExample implements Example {

    public void runExample(EntityManager entityManager) {
        Toothbrush toothbrush = new Toothbrush(true);
        entityManager.persist(toothbrush);

        Person person = new Person("Onur", toothbrush);
        entityManager.persist(person);
    }
}
