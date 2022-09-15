package com.multidb.algorithm.DesignPattern.ZDemo;

public class StrategyDemo {
	public static void main(String[] args) {
		Context contextAdd = new Context(new Add());
		System.out.println("5 + 10 = " + contextAdd.execute(5, 10));
		
		Context contextSubstract = new Context(new Substract());
		System.out.println("5 - 10 = " + contextSubstract.execute(5, 10));
	}
}
