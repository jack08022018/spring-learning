package com.springaop.algorithm.DesignPattern.TemplateMethod;

public class Football extends Game {
    @Override
    protected void initialize() {
        System.out.println("Init football");
    }

    @Override
    protected void startPlay() {
        System.out.println("start play football");
    }

    @Override
    protected void endPlay() {
        System.out.println("end play football");
    }
}
