package com.oonurkuru.jpa.domains.Relations.OneToMany_ManyToOne;

import javax.persistence.*;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Student ve School sınıfları arasında 1-N ilişki kurulmuştur. 1 tarafını School N tarafını Student temsil eder.
 * İki yönlü ilişki kurulmuştur ve bu yüzden otomatik olarak baskın taraf Student sınıfı olmuştur.
 * JoinColumn notasyonu ile School sınıfına ait foreign keyin bu tabloda tutulacağı belirtilmiştir.
 */
@Entity
@Table(name = "O_T_M_STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STUDENT_ID")
    private Integer studentId;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "SCHOOL")
    private School school;

    public Student() {
    }

    public Student(String name, School school) {
        this.name = name;
        this.school = school;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
