package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.annotations.ExampleClassCode;
import com.oonurkuru.jpa.domains.CompositePrimaryKey.IdClass.Project;

import javax.persistence.EntityManager;

/**
 * Created by Onur Kuru on 16.7.2017.
 */
@ExampleClassCode(classCode = "9")
public class IdClassExample implements Example {

    @Override
    public void runExample(EntityManager entityManager) {

        Project project = new Project(1, 1, "JPA BASICS");
        entityManager.persist(project);

    }
}
