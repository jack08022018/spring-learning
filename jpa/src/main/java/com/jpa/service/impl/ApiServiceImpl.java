package com.jpa.service.impl;

import com.jpa.repository.RentalRepository;
import com.jpa.repository.dto.MovieRentalDto;
import com.jpa.repository.dto.MovieRentalRecord;
import com.jpa.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public <T> List<T> getRentalMovies(String title) {
        return (List<T>) rentalRepository.getRentalMoviesProjection(title);
//        return (List<T>) rentalRepository.getRentalMoviesTuple(title).stream()
//                .map(s -> {
//                    Timestamp rental_date = (Timestamp) s.get("rental_date");
//                    return new MovieRentalDto().builder()
//                                    .title((String) s.get("title"))
//                                    .rentalDate(rental_date.toLocalDateTime())
//                                    .build();
//                })
//                .collect(Collectors.toList());
//        return (List<T>) rentalRepository.getRentalMoviesRecord(title).getRentalMoviesRecord(title);
    }

}
