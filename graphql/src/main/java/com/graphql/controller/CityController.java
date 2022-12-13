package com.graphql.controller;


import com.graphql.configuration.exceptions.CommonException;
import com.graphql.dto.CityDto;
import com.graphql.entity.CityEntity;
import com.graphql.repository.CityRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
//@Validated
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @QueryMapping(value = "findCity")
    public List<CityEntity> findCity(@Valid @Argument CityDto city) throws CommonException {
//        int a = 1/0;
        return cityRepository.findByCity(city.getCityName());
    }

    @QueryMapping(value = "findCityByName")
    public List<CityEntity> findCityByName(
            @Argument String city,

            @Min(value = 10, message = "id >= 10")
            @Argument Integer id) {
        return cityRepository.findByCity(city);
    }

//    @MutationMapping(value = "createPerson")
//    public void addPerson(@Argument CityDto city) {
//
//    }

}
