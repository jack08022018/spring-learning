package com.springaop.algorithm.DesignPattern.ZDemo;

public class Coke extends ColdDrink {

	@Override
	public String name() {
		return "Coke";
	}

	@Override
	public float price() {
		return (float) 2.2;
	}

}
