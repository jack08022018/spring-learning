package com.multidb.algorithm.DesignPattern.BuilderPattern;

import java.util.ArrayList;
import java.util.List;

import com.multidb.algorithm.DesignPattern.BuilderPattern.item.Item;

public class Meal {
	private List<Item> items = new ArrayList<>();
	public void addItem(Item item) {
		items.add(item);
	}
	public double getCost() {
		double cost = 0;
		for (Item item : items) {
			cost += item.price();
		}
		return cost;
	}
	public void showItems() {
		for (Item item : items) {
			System.out.println("  - " + item.name() + ": " + item.packing().pack());
		}
	}
}
