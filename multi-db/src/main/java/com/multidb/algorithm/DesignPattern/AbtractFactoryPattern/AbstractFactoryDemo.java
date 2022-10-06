package com.multidb.algorithm.DesignPattern.AbtractFactoryPattern;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        AbstractFactoryDemo demo = new AbstractFactoryDemo();
        demo.buttonAction("WIN");
        demo.buttonAction("MAC");
    }

    private void buttonAction(String platform) {
        GUIFactory factory = FactoryProducer.getFactory(platform);
        Button button = factory.createButton();
        button.pain();
    }
}
