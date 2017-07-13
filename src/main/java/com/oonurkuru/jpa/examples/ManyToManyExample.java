package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.domains.Relations.ManyToMany.Lesson;
import com.oonurkuru.jpa.domains.Relations.ManyToMany.Student;

import javax.persistence.EntityManager;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * JPA Relation özelliği, Many To Many relation için hazırlanmış test kodu.
 */
public class ManyToManyExample implements Example {

    public void runExample(EntityManager entityManager) {
        Lesson lesson = new Lesson("Matematik");
        Student student = new Student("Onur");

        lesson.getStudentList().add(student);
        student.getLessonList().add(lesson);

        entityManager.persist(student);
        entityManager.persist(lesson);
    }

}
