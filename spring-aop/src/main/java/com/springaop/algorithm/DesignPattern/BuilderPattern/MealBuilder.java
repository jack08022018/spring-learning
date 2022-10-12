package com.springaop.algorithm.DesignPattern.BuilderPattern;

import com.springaop.algorithm.DesignPattern.BuilderPattern.item.Burger.ChickenBurger;
import com.springaop.algorithm.DesignPattern.BuilderPattern.item.Burger.VegBurger;
import com.springaop.algorithm.DesignPattern.BuilderPattern.item.ColdDrink.Coke;
import com.springaop.algorithm.DesignPattern.BuilderPattern.item.ColdDrink.Pepsi;

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
