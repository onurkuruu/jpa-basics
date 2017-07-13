package com.oonurkuru.jpa.domains.Inheritance.TablePerClassStrategy;

import javax.persistence.*;

/**
 * Created by Onur Kuru on 13.7.2017.
 */

/**
 * Hayvan sınıfları için temel sınıfı tanımlar.
 * Kalıtım stratejisi olarak TABLE_PER_CLASS kullanılır.
 */
@Entity
@Table(name = "T_P_ANIMAL")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Animal {

    /**
     * Hayvanlar için primary key alanını belirtir.
     * GeneratedValue stratejisi AUTO olarak kullanılmıştır.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ANIMAL_ID")
    private Integer animalId;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "NUMBER_OF_LEGS")
    private Integer numberOfLegs;

    @Column(name = "VEGETARIAN")
    private Boolean vegetarian;

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(Integer numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }
}
