package com.multidb.algorithm.DesignPattern.AAA;

public class DecoBlood extends AnimalDeco {
    public DecoBlood(Animal animal) {
        super(animal);
    }

    @Override
    public void describe() {
        animal.describe();
        System.out.println("hot blood");
    }
}
