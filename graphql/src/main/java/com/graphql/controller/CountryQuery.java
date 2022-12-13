package com.graphql.controller;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphql.dto.CountryDto;
import com.graphql.entity.CountryEntity;
import com.graphql.repository.CountryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Component
@Validated
public class CountryQuery implements GraphQLQueryResolver {
    @Autowired
    private CountryRepository countryRepository;

    public List<CountryEntity> findCountry(@Valid @Argument CountryDto country) {
//        int a = 1/0;
        return countryRepository.findByCountry(country.getCountryName());
    }

}
