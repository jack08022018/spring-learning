package com.jpa.dao;

import com.jpa.dto.MovieRentalDto;

import java.util.List;

public interface RentalDao {
    List<MovieRentalDto> getRentalMovies(String title);
}
