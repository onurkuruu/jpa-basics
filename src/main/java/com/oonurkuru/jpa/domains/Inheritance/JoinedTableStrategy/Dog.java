package com.oonurkuru.jpa.domains.Inheritance.JoinedTableStrategy;

import javax.persistence.*;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Animal sınıfından kalıtım bu sınıf kalıtım stratejisi olarak JOINED kullanır
 * primary key alanları Animal sınıfının animalId alanı tarafından sağlanır.
 */
@Entity
@Table(name = "J_T_DOG")
@PrimaryKeyJoinColumn(referencedColumnName = "ANIMAL_ID")
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
