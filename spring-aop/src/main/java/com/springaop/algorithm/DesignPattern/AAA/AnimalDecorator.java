package com.springaop.algorithm.DesignPattern.AAA;

public abstract class AnimalDecorator implements Animal {
    protected Animal animal;

    AnimalDecorator(Animal animal) {
        this.animal = animal;
    }

}
