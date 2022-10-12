package com.springaop.algorithm.DesignPattern.BuilderPattern;

public class BuilderPatternDemo {
	public static void main(String[] args) {
		MealBuilder builder = new MealBuilder();
		Meal nonVegMeal = builder.prepareNonVegMeal();
		System.out.println("Non Veg Meal");
		System.out.println("--------------------------");
		nonVegMeal.showItems();
		System.out.println("  Cost: " + nonVegMeal.getCost());
		
		System.out.println("\nVeg Meal");
		System.out.println("--------------------------");
		Meal vegMeal = builder.prepareVegMeal();
		vegMeal.showItems();
		System.out.println("  Cost: " + vegMeal.getCost());
	}
}
