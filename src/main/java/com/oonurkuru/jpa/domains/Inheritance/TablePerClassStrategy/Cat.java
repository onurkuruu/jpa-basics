package com.oonurkuru.jpa.domains.Inheritance.TablePerClassStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

@Entity
@Table(name = "T_P_CAT")
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
