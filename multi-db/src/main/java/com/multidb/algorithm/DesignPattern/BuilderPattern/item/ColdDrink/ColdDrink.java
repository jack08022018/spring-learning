package com.multidb.algorithm.DesignPattern.BuilderPattern.item.ColdDrink;

import algorithm.DesignPattern.BuilderPattern.item.Item;
import algorithm.DesignPattern.BuilderPattern.packing.Bottle;
import algorithm.DesignPattern.BuilderPattern.packing.Packing;

public abstract class ColdDrink implements Item {

	@Override
	public Packing packing() {
		return new Bottle();
	}
}
