package com.oonurkuru.jpa.domains.Relations.OneToOne;

import javax.persistence.*;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Person sınıfı ile Toothbrush sınıfları arasında bire bir ilişki kurulmuştur. İlişki çift yönlüdür.
 * Baskın taraf olarak Person sınıfı belirlenmiştir. Bunun anlamı JoinColumn olarak belirtilen alanına
 * Toothbrush sınıfına primary key alanları yazılır.
 *
 */
@Entity
@Table(name = "O_T_O_PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PERSON_ID")
    private Integer personId;

    @Column(name = "PERSON_NAME")
    private String personName;

    @OneToOne
    @JoinColumn(name = "TOOTHBRUSH")
    private Toothbrush toothbrush;

    public Person() {
    }

    public Person(String personName, Toothbrush toothbrush) {
        this.personName = personName;
        this.toothbrush = toothbrush;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Toothbrush getToothbrush() {
        return toothbrush;
    }

    public void setToothbrush(Toothbrush toothbrush) {
        this.toothbrush = toothbrush;
    }
}
