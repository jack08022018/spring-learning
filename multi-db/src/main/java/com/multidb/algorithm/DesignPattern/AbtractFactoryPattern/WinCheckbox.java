package com.multidb.algorithm.DesignPattern.AbtractFactoryPattern;

public class WinCheckbox implements Checkbox {
    @Override
    public void pain() {
        System.out.println("Render a checkbox in Window style.");
    }
}
