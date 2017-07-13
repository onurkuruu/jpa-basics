package com.oonurkuru.jpa.domains.Relations.OneToMany_ManyToOne;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Student ve School sınıfları arasında 1-N ilişki kurulmuştur. 1 tarafını School N tarafını Student temsil eder.
 * İki yönlü ilişki kurulmuştur ve bu yüzden otomatik olarak baskın taraf Student sınıfı olmuştur.
 * mappedBy ile baskın tarafın Student sınıfı olduğu belirtilmiştir.
 */
@Entity
@Table(name = "O_T_M_SCHOOL")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SCHOOL_ID")
    private Integer schoolId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "school")
    private List<Student> studentList;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
