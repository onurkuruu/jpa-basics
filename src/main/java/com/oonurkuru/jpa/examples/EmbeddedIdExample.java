package com.oonurkuru.jpa.examples;

import com.oonurkuru.jpa.annotations.ExampleClassCode;
import com.oonurkuru.jpa.domains.CompositePrimaryKey.EmbeddedId.Project;
import com.oonurkuru.jpa.domains.CompositePrimaryKey.EmbeddedId.ProjectIdClass;

import javax.persistence.EntityManager;

/**
 * Created by Onur Kuru on 16.7.2017.
 */
@ExampleClassCode(classCode = "10")
public class EmbeddedIdExample implements Example {

    @Override
    public void runExample(EntityManager entityManager) {

        ProjectIdClass projectIdClass = new ProjectIdClass(1, 1);
        Project project = new Project(projectIdClass, "JPA BASICS");

        entityManager.persist(project);
    }
}
