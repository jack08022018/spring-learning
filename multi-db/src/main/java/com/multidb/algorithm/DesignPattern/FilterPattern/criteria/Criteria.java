package com.multidb.algorithm.DesignPattern.FilterPattern.criteria;

import java.util.List;

import algorithm.DesignPattern.FilterPattern.Person;

public interface Criteria {
	public List<Person> meetCriteria(List<Person> persons);
}
