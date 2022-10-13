package com.springaop.algorithm.DesignPattern.AAA;

public class BloodDecorator extends AnimalDecorator {
    BloodDecorator(Animal animal) {
        super(animal);
    }

    @Override
    public void describe() {
        animal.describe();
        describeBlood();
    }

    private void describeBlood() {
        System.out.println("red blood");
    }
}
