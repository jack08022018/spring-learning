package com.multidb.algorithm.DesignPattern.AAA;

public class Demo {
    public static void main(String[] args) {
        Animal bird = new DecoBlood(new Bird());
        bird.describe();
    }
}
