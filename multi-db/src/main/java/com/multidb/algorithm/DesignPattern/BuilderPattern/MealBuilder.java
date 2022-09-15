package com.multidb.algorithm.DesignPattern.BuilderPattern;

import algorithm.DesignPattern.BuilderPattern.item.Burger.ChickenBurger;
import algorithm.DesignPattern.BuilderPattern.item.Burger.VegBurger;
import algorithm.DesignPattern.BuilderPattern.item.ColdDrink.Coke;
import algorithm.DesignPattern.BuilderPattern.item.ColdDrink.Pepsi;

public class MealBuilder {
	public Meal prepareVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new VegBurger());
		meal.addItem(new Pepsi());
		return meal;
	}
	public Meal prepareNonVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new ChickenBurger());
		meal.addItem(new Coke());
		return meal;
	}
}
