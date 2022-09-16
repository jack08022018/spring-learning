package com.multidb.algorithm.DesignPattern.BuilderPattern.item.ColdDrink;

import com.multidb.algorithm.DesignPattern.BuilderPattern.item.Item;
import com.multidb.algorithm.DesignPattern.BuilderPattern.packing.Bottle;
import com.multidb.algorithm.DesignPattern.BuilderPattern.packing.Packing;

public abstract class ColdDrink implements Item {

	@Override
	public Packing packing() {
		return new Bottle();
	}
}
