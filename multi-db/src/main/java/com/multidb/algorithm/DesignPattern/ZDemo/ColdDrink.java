package com.multidb.algorithm.DesignPattern.ZDemo;

public abstract class ColdDrink implements Item {
	
	@Override
	public Packing packing() {
		return new Bottle();
	}
}
