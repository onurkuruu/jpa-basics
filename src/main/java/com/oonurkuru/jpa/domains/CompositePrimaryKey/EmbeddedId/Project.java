package com.oonurkuru.jpa.domains.CompositePrimaryKey.EmbeddedId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Onur Kuru on 16.7.2017.
 */

@Entity
@Table(name = "E_I_PROJECT")
public class Project {

    @EmbeddedId
    private ProjectIdClass projectId;

    @Column(name = "NAME")
    private String name;

    public Project(ProjectIdClass projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }

    public Project() {
    }

    public ProjectIdClass getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectIdClass projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
