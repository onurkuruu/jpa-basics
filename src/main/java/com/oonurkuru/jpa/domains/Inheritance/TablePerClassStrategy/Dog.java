package com.oonurkuru.jpa.domains.Inheritance.TablePerClassStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

@Entity
@Table(name = "T_P_DOG")
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
