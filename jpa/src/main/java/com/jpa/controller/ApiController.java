package com.jpa.controller;


import com.jpa.entity.relationship.ActorEntity;
import com.jpa.entity.relationship.FilmEntity;
import com.jpa.repository.ActorRepository;
import com.jpa.repository.CityRepository;
import com.jpa.repository.CountryRepository;
import com.jpa.repository.FilmRepository;
import com.jpa.service.ApiService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @GetMapping(value = "/getRentalMovies")
    public <T> List<T> getRentalMovies(@RequestParam("title") String title) {
        return apiService.getRentalMovies(title);
    }

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping(value = "/delete")
    public <T> T delete() {
        apiService.testJpaSave();
        return (T) "country";
//        return (T) actorRepository.findById(207).get();
    }

}
