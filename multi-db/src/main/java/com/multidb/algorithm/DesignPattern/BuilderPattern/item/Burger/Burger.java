package com.multidb.algorithm.DesignPattern.BuilderPattern.item.Burger;

import algorithm.DesignPattern.BuilderPattern.item.Item;
import algorithm.DesignPattern.BuilderPattern.packing.Packing;
import algorithm.DesignPattern.BuilderPattern.packing.Wrapper;

public abstract class Burger implements Item {

	@Override
	public Packing packing() {
		return new Wrapper();
	}
}
