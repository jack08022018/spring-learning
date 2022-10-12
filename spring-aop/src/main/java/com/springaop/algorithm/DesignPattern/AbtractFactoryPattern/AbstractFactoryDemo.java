package com.springaop.algorithm.DesignPattern.AbtractFactoryPattern;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        GuiFactory factory = FactoryProducer.getFactory(Platform.MAC);
        Button button = factory.createButton();
        button.pain();
    }

}
