package com.multidb.algorithm.DesignPattern.ZDemo;

public abstract class Burger implements Item {
	
	@Override
	public Packing packing() {
		return new Wrapper();
	}
}
