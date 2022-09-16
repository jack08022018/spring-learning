package com.multidb.algorithm.DesignPattern.BuilderPattern;

import com.multidb.algorithm.DesignPattern.BuilderPattern.item.Burger.ChickenBurger;
import com.multidb.algorithm.DesignPattern.BuilderPattern.item.Burger.VegBurger;
import com.multidb.algorithm.DesignPattern.BuilderPattern.item.ColdDrink.Coke;
import com.multidb.algorithm.DesignPattern.BuilderPattern.item.ColdDrink.Pepsi;

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
