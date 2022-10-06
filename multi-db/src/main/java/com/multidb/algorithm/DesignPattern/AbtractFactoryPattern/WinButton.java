package com.multidb.algorithm.DesignPattern.AbtractFactoryPattern;

public class WinButton implements Button {
    @Override
    public void pain() {
        System.out.println("Render a button in Windows style.");
    }
}
