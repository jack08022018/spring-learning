package com.multidb.algorithm.DesignPattern.AbtractFactoryPattern;

public class WinFactory extends GUIFactory {
    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}
