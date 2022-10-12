package com.springaop.algorithm.DesignPattern.ZDemo;

public class ChickenBurger extends Burger {

	@Override
	public String name() {
		return "Chicken Burger";
	}

	@Override
	public float price() {
		return 5;
	}

}
