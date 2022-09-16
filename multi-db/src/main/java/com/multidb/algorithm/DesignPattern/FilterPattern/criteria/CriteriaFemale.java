package com.multidb.algorithm.DesignPattern.FilterPattern.criteria;

import java.util.ArrayList;
import java.util.List;

import com.multidb.algorithm.DesignPattern.FilterPattern.Person;

public class CriteriaFemale implements Criteria {
	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> femalePersons = new ArrayList<Person>(); 
	      
		for (Person person : persons) {
			if(person.getGender().equalsIgnoreCase("FEMALE")){
				femalePersons.add(person);
			}
		}
		return femalePersons;
	}
}
