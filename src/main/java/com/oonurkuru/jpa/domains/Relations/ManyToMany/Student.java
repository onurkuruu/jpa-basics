package com.oonurkuru.jpa.domains.Relations.ManyToMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Student ve Lesson sınıfları arasında M-M ilişki kurulmuştur. Baskın taraf olarak Student sınıfı belirlenmiştir.
 * JoinTable notasyonu ile oluşacak ara tabloya ait özellikler girilmiştir. Bu tabloda Student ve Lesson tablolarına ait
 * primary key alanları eşleştirilerek iki tablo arasında ilişki sağlanır.
 */
@Entity
@Table(name = "M_T_M_STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STUDENT_ID")
    private Integer studentId;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "M_T_M_STUDENT_LESSON",
            joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "LESSON_ID", referencedColumnName = "LESSON_ID")
    )
    private List<Lesson> lessonList;

    public Student() {
    }

    public Student(String name) {
        this.lessonList = new ArrayList<Lesson>();
        this.name = name;
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

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }
}
