package com.springaop.algorithm.DesignPattern.TemplateMethod;

public abstract class Game {
    abstract protected void initialize();
    abstract protected void startPlay();
    abstract protected void endPlay();

    protected void play() {
        initialize();
        startPlay();
        endPlay();
    }
}
