package com.multidb.algorithm.DesignPattern.ZDemo;

import java.util.ArrayList;
import java.util.List;

public class Meal {
	private List<Item> items = new ArrayList<Item>();
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public float getCost() {
		float cost = 0;
		for (Item item : items) {
			cost += item.price();
		}
		return cost;
	}
	
	public void showItems() {
		for (Item item : items) {
			System.out.print(item.name() + ", " + item.price() + ", " + item.packing().pack() + "\n");
		}
	}
	
}
