package com.oonurkuru.jpa.domains.CompositePrimaryKey.IdClass;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Onur Kuru on 16.7.2017.
 */

@Entity
@Table(name = "I_C_PROJECT")
//@IdClass(ProjectIdClass.class)
public class Project implements Serializable{

    @Id
    @Column(name = "PROJECT_ID")
    private Integer projectId;

    @Id
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "NAME")
    private String name;

    public Project() {
    }

    public Project(Integer projectId, Integer version, String name) {
        this.projectId = projectId;
        this.version = version;
        this.name = name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
