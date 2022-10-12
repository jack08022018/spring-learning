package com.springaop.algorithm.DesignPattern.TemplateMethod;

public class TemplateMethodDemo {
    public static void main(String[] args) {
        Game game = new Dota();
        game.play();

        System.out.println();

        game = new Football();
        game.play();
    }
}
