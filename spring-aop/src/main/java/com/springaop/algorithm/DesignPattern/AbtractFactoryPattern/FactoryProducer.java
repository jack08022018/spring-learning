package com.springaop.algorithm.DesignPattern.AbtractFactoryPattern;

public class FactoryProducer {
    public static GUIFactory getFactory(String platform) {
        if(platform.equals("WIN")) {
            return new WinFactory();
        }else if(platform.equals("MAC")) {
            return new MacFactory();
        }
        return null;
    }
}
