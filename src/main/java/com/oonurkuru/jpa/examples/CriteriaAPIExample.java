package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.annotations.ExampleClassCode;
import com.oonurkuru.jpa.domains.Relations.ManyToMany.Lesson;
import com.oonurkuru.jpa.domains.Relations.ManyToMany.Student;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Onur Kuru on 16.7.2017.
 */
@ExampleClassCode(classCode = "7")
public class CriteriaAPIExample implements Example {

    @Override
    public void runExample(EntityManager entityManager) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<Student> from = criteriaQuery.from(Student.class);

        CriteriaQuery<Object> select = criteriaQuery.select(from);
        TypedQuery<Object> typedQuery = entityManager.createQuery(select);
        List<Object> resultList = typedQuery.getResultList();

        if (resultList.size() < 1) {
            Example example = new ManyToManyExample();
            example.runExample(entityManager);
            resultList = typedQuery.getResultList();
        }

        StringBuilder stringBuilder = new StringBuilder("Tüm Kayıtlar.\n");

        for (Object object : resultList) {
            Student student = (Student) object;
            stringBuilder.append("User Id: ");
            stringBuilder.append(student.getStudentId());
            stringBuilder.append(" İsim: ");
            stringBuilder.append(student.getName());
            stringBuilder.append(" Dersleri:");

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
