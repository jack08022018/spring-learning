package com.jpa.service;

import java.util.List;

public interface ApiService {
    <T> List<T> getRentalMovies(String title);
}