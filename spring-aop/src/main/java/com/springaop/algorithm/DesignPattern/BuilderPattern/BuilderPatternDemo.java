package com.springaop.algorithm.DesignPattern.BuilderPattern;

public class BuilderPatternDemo {
	public static void main(String[] args) {
		Meal nonVegMeal = new NonVegMealBuilder()
				.prepareMeal()
				.build();
		System.out.println("Non Veg Meal");
		System.out.println("--------------------------");
		nonVegMeal.showItems();
		System.out.println("  Cost: " + nonVegMeal.getCost());
		
		System.out.println("\nVeg Meal");
		System.out.println("--------------------------");
		Meal vegMeal = new VegMealBuilder()
				.prepareMeal()
				.build();
		vegMeal.showItems();
		System.out.println("  Cost: " + vegMeal.getCost());

		Computer computer = new Computer.Builder()
				.setRAM("2 GB")
				.setHDD("500 GB")
				.build();
	}
}
