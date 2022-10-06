package com.multidb.algorithm.DesignPattern.AbtractFactoryPattern;

public class MacFactory extends GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
