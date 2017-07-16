package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.annotations.ExampleClassCode;
import com.oonurkuru.jpa.domains.Relations.ManyToMany.Lesson;
import com.oonurkuru.jpa.domains.Relations.ManyToMany.Student;

import javax.persistence.EntityManager;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * JPA Relation özelliği, Many To Many relation için hazırlanmış test kodu.
 */

@ExampleClassCode(classCode = "2")
public class ManyToManyExample implements Example {

    public void runExample(EntityManager entityManager) {
        Lesson lesson1 = new Lesson("Diferansiyel Denklemler");
        Lesson lesson2 = new Lesson("Veri Yapıları");
        Lesson lesson3 = new Lesson("IOT");

        Student student1 = new Student("Onur");
        Student student2 = new Student("Ahmet");
        Student student3 = new Student("Mehmet");
        Student student4 = new Student("Veli");

        lesson1.getStudentList().add(student1);
        lesson1.getStudentList().add(student2);
        lesson1.getStudentList().add(student3);

        student1.getLessonList().add(lesson1);
        student2.getLessonList().add(lesson1);
        student3.getLessonList().add(lesson1);

        lesson2.getStudentList().add(student4);
        student4.getLessonList().add(lesson2);

        lesson3.getStudentList().add(student1);
        lesson3.getStudentList().add(student4);

        student1.getLessonList().add(lesson3);
        student4.getLessonList().add(lesson3);

        entityManager.persist(lesson1);
        entityManager.persist(lesson2);
        entityManager.persist(lesson3);

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);
    }
}
