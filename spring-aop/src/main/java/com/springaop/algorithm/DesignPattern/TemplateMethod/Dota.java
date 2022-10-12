package com.springaop.algorithm.DesignPattern.TemplateMethod;

public class Dota extends Game {
    @Override
    protected void initialize() {
        System.out.println("Init dota");
    }

    @Override
    protected void startPlay() {
        System.out.println("start play dota");
    }

    @Override
    protected void endPlay() {
        System.out.println("end play dota");
    }
}
