package com.jpa.service.impl;

import com.jpa.dao.RentalDao;
import com.jpa.entity.relationship.ActorEntity;
import com.jpa.entity.relationship.CountryEntity;
import com.jpa.entity.relationship.FilmEntity;
import com.jpa.repository.*;
import com.jpa.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalDao rentalDao;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public <T> List<T> getRentalMovies(String title) {
//        return (List<T>) rentalRepository.getRentalMoviesProjection(title);

//        return (List<T>) rentalRepository.getRentalMoviesTuple(title).stream()
//                .map(s -> {
//                    Timestamp rental_date = (Timestamp) s.get("rental_date");
//                    return new MovieRentalDto().builder()
//                                    .title((String) s.get("title"))
//                                    .rentalDate(rental_date.toLocalDateTime())
//                                    .build();
//                })
//                .collect(Collectors.toList());

//        return (List<T>) rentalRepository.getRentalMoviesDto(title);

//        return (List<T>) rentalDao.getRentalMovies(title);

        return (List<T>) countryRepository.findAllById(Collections.singleton(999));
    }

    @Override
    @Transactional
    public void testJpaSave() {
        CountryEntity country = countryRepository.getReferenceById(999);
//        country.setCountry("Wakanda");
//        countryRepository.save(country);
//        CityEntity city = country.getCities().get(0);
//        city.getCountry();
//        CountryEntity country = city.getCountry();
//        country.getCities().remove(city);
//        countryRepository.save(country);
//        countryRepository.deleteById(999);

//        ActorEntity actor = ActorEntity.builder()
//                .firstName("Nhung")
//                .lastName("Hoang")
//                .lastUpdate(LocalDateTime.now())
//                .build();
//        FilmEntity film = FilmEntity.builder()
//                .title("star wars")
//                .lastUpdate(LocalDateTime.now())
//                .languageId(1)
//                .build();
//
//        actor.addFilms(Arrays.asList(film));
//        filmRepository.save(film);
//        actorRepository.save(actor);
    }

}
