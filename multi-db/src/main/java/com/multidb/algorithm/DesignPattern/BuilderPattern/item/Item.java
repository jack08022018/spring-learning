package com.multidb.algorithm.DesignPattern.BuilderPattern.item;

import com.multidb.algorithm.DesignPattern.BuilderPattern.packing.Packing;

public interface Item {
	public double price();
	public String name();
	public Packing packing();
}
