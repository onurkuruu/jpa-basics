package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.annotations.ExampleClassCode;
import com.oonurkuru.jpa.domains.Relations.ManyToMany.Lesson;
import com.oonurkuru.jpa.domains.Relations.ManyToMany.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Onur Kuru on 16.7.2017.
 */
@ExampleClassCode(classCode = "8")
public class NamedQueryExample implements Example {

    @Override
    public void runExample(EntityManager entityManager) {

//        Query query = entityManager.createQuery("select s from Student s");
        Query query = entityManager.createNamedQuery("Student.findAllStudents", Student.class);
        List<Student> students = query.getResultList();

        if (students.size() < 1) {
            Example example = new ManyToManyExample();
            example.runExample(entityManager);
            students = query.getResultList();
        }

        StringBuilder stringBuilder = new StringBuilder("Tüm Kayıtlar.\n");

        for (Object object : students) {
            Student student = (Student) object;
            stringBuilder.append("User Id: ");
            stringBuilder.append(student.getStudentId());
            stringBuilder.append(" İsim: ");
            stringBuilder.append(student.getName());
            stringBuilder.append(" Dersleri: ");

            for (Lesson lesson : student.getLessonList()) {
                stringBuilder.append(" ");
                stringBuilder.append(lesson.getName());
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }

        System.out.print(stringBuilder.toString());
    }
}
