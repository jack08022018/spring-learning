package com.graphql.controller;


import com.graphql.configuration.exceptions.CommonException;
import com.graphql.dto.CityDto;
import com.graphql.dto.CountryDto;
import com.graphql.entity.CityEntity;
import com.graphql.entity.CountryEntity;
import com.graphql.repository.CityRepository;
import com.graphql.repository.CountryRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ApiController {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @QueryMapping(value = "findCity")
    public List<CityEntity> findCity(@Valid @Argument CityDto city) throws CommonException {
//        int a = 1/0;
        return cityRepository.findByCity(city.getCityName());
    }

    @QueryMapping(value = "findCityByName")
    public List<CityEntity> findCityByName(
            @Argument String city,
            @Min(value = 10, message = "id >= 10") @Argument Integer id) {
        return cityRepository.findByCity(city);
    }

    @MutationMapping(value = "createCountry")
    public Boolean createCountry(@Argument CountryDto dto) {
        CountryEntity entity = CountryEntity.builder()
                .country(dto.getCountryName())
                .lastUpdate(LocalDateTime.now())
                .build();
        countryRepository.save(entity);
        return true;
    }

}
