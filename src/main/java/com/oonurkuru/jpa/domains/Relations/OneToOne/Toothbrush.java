package com.oonurkuru.jpa.domains.Relations.OneToOne;

import javax.persistence.*;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Person sınıfı ile Toothbrush sınıfları arasında bire bir ilişki kurulmuştur. İlişki çift yönlüdür.
 * Pasif taraf olarak Toothbrush sınıfı belirlenmiştir ve mappedBy özelliği eklenerek bu gösterilmiştir.
 * Bu sayede Toothbrush tablosunda Person sınıfına ait bir foreign key oluşturulmayacaktır.
 */
@Entity
@Table(name = "O_T_O_TOOTHBRUSH")
public class Toothbrush {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOOTHBRUSH_ID")
    private Integer toothbrushId;

    @Column(name = "SOFT")
    private Boolean soft;

    @OneToOne(mappedBy = "toothbrush", fetch = FetchType.LAZY)
    private Person person;

    public Toothbrush() {
    }

    public Toothbrush(Boolean soft) {
        this.soft = soft;
    }

    public Integer getToothbrushId() {
        return toothbrushId;
    }

    public void setToothbrushId(Integer toothbrushId) {
        this.toothbrushId = toothbrushId;
    }

    public Boolean getSoft() {
        return soft;
    }

    public void setSoft(Boolean soft) {
        this.soft = soft;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
