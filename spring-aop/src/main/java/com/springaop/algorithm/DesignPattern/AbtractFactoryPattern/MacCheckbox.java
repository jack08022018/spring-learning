package com.springaop.algorithm.DesignPattern.AbtractFactoryPattern;

public class MacCheckbox implements Checkbox {
    @Override
    public void pain() {
        System.out.println("Render a checkbox in MacOS style.");
    }
}
