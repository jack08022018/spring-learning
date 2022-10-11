package com.multidb.algorithm.DesignPattern.AAA;

public abstract class AnimalDeco implements Animal {
    protected Animal animal;

    public AnimalDeco(Animal animal) {
        this.animal = animal;
    }
}
