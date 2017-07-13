package com.oonurkuru.jpa.domains.Inheritance.SingleTableStrategy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Veri tabanında ANIMAL_TYPE alanının CAT değerleri bu entity sınıfını işaret eder
 * Bu özellik DiscriminatorValue ile sağlanmıştır.
 */
@Entity
@DiscriminatorValue(value = "CAT")
public class Cat extends Animal {

    @Column(name = "HUNTER")
    private Boolean hunter;

    public Cat(String owner, Integer numberOfLegs, Boolean vegetarian, Boolean hunter) {
        this.setOwner(owner);
        this.setNumberOfLegs(numberOfLegs);
        this.setVegetarian(vegetarian);
        this.setHunter(hunter);
    }

    public Cat() {
    }

    public Boolean getHunter() {
        return hunter;
    }

    public void setHunter(Boolean hunter) {
        this.hunter = hunter;
    }
}
