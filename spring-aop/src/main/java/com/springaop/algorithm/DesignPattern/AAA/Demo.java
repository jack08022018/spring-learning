package com.springaop.algorithm.DesignPattern.AAA;

public class Demo {
    public static void main(String[] args) {
        Animal animal = new BloodDecorator(new Bird());
        animal.describe();
    }
}
