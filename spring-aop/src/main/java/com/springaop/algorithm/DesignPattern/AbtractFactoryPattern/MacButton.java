package com.springaop.algorithm.DesignPattern.AbtractFactoryPattern;

public class MacButton implements Button {
    @Override
    public void pain() {
        System.out.println("Render a button in MacOS style.");
    }
}
