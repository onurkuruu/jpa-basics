package com.oonurkuru.jpa.domains.CompositePrimaryKey.EmbeddedId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Onur Kuru on 16.7.2017.
 */

@Embeddable
public class ProjectIdClass implements Serializable {

    @Column(name = "PROJECT_ID")
    private Integer projectId;

    @Column(name = "VERSION")
    private Integer version;

    public ProjectIdClass() {
    }

    public ProjectIdClass(Integer projectId, Integer version) {
        this.projectId = projectId;
        this.version = version;
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
}
