package com.multidb.algorithm.DesignPattern.ZDemo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Java8Demo {
	public static void main(String[] args) {
		List<Movie> movies = Arrays.asList(
		        new Movie("Lord of the rings", 8.8, true),
		        new Movie("Back to the future", 8.5, false),
		        new Movie("Back to the future", 8.1, false),
		        new Movie("Carlito's way", 7.9, true),
		        new Movie("Pulp fiction", 8.9, false));
		movies.sort(Comparator.comparing(Movie::getTitle)
							.reversed()
							.thenComparing(Movie::getRating, (s1, s2) -> {
							    return s2 - s1 > 0 ? 1 : -1;
							}));
//							.reversed());
//		movies.forEach(x -> System.out.println(x.getRating() + ", " + x.isStarred() + ", " + x.getTitle()));
		
		List<Integer> numbers = Arrays.asList(1,2,5,7);
		numbers = numbers.stream().filter(Java8Demo::isOdd).collect(Collectors.toList());
		numbers.forEach(System.out::println);
	}
	
	public static boolean isOdd(int i) {
		return i%2 != 0;
	}
	
}

class Movie {
	private String title;
	private double rating;
	private boolean starred;
	
	public Movie(String title, double rating, boolean starred) {
		this.title = title;
		this.rating = rating;
		this.starred = starred;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public boolean isStarred() {
		return starred;
	}
	public void setStarred(boolean starred) {
		this.starred = starred;
	}
	
}
