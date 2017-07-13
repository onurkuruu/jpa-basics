package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.domains.Relations.OneToMany_ManyToOne.School;
import com.oonurkuru.jpa.domains.Relations.OneToMany_ManyToOne.Student;

import javax.persistence.EntityManager;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * JPA Relation özelliği, One To Many relation için hazırlanmış test kodu
 */
public class OneToManyExample implements Example {

    public void runExample(EntityManager entityManager) {
        School school = new School("Ankara");
        entityManager.persist(school);

        Student student = new Student("Onur", school);
        entityManager.persist(student);
    }
}
