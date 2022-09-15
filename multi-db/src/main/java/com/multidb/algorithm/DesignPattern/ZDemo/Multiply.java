package com.multidb.algorithm.DesignPattern.ZDemo;

public class Multiply implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1*num2;
	}

}
