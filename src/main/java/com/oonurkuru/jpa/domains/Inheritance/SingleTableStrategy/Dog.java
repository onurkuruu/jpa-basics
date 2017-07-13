package com.oonurkuru.jpa.domains.Inheritance.SingleTableStrategy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Veri tabanında ANIMAL_TYPE alanının DOG değerleri bu entity sınıfını işaret eder
 * Bu özellik DiscriminatorValue ile sağlanmıştır.
 */
@Entity
@DiscriminatorValue(value = "DOG")
public class Dog extends Animal {

    @Column(name = "ROLL_OVER")
    private Boolean rollOver;

    public Dog(String owner, Integer numberOfLegs, Boolean vegetarian, Boolean rollOver) {
        this.setOwner(owner);
        this.setNumberOfLegs(numberOfLegs);
        this.setVegetarian(vegetarian);
        this.setRollOver(rollOver);
    }

    public Dog() {
    }

    public Boolean getRollOver() {
        return rollOver;
    }

    public void setRollOver(Boolean rollOver) {
        this.rollOver = rollOver;
    }
}
