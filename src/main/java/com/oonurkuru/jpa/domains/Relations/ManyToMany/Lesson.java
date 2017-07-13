package com.oonurkuru.jpa.domains.Relations.ManyToMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Onur Kuru on 13.7.2017.
 */


/**
 * Student ve Lesson sınıfları arasında M-M ilişki kurulmuştur. Baskın taraf olarak Student sınıfı belirlenmiştir.
 * mappedBy notasyonu ile baskın tarafın Student sınıfı olduğu belirtilmiştir.
 */
@Entity
@Table(name = "M_T_M_LESSON")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LESSON_ID")
    private Integer lessonId;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "lessonList")
    private List<Student> studentList;

    public Lesson() {
    }

    public Lesson(String name) {
        this.studentList = new ArrayList<Student>();
        this.name = name;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
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
