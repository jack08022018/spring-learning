package com.jpa.service.impl;

import com.jpa.dao.RentalDao;
import com.jpa.entity.EmployeeEntity;
import com.jpa.entity.SalariesEntity;
import com.jpa.entity.relationship.ActorEntity;
import com.jpa.entity.relationship.CityEntity;
import com.jpa.entity.relationship.CountryEntity;
import com.jpa.entity.relationship.FilmEntity;
import com.jpa.enumerator.Gender;
import com.jpa.repository.*;
import com.jpa.service.ActorService;
import com.jpa.service.ApiService;
import com.jpa.service.CityService;
import com.jpa.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private ActorService actorService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalariesRepository salariesRepository;

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
    public <T> T testJpaSave() {
        CountryEntity country = countryRepository.getReferenceById(999);
        country.setCountry("Wakanda");
        countryRepository.save(country);
//        CityEntity city = country.getCities().get(0);
//        city.getCountry();
//        CountryEntity country = city.getCountry();
//        country.getCities().remove(city);
//        countryRepository.save(country);
//        countryRepository.deleteById(999);
        return (T) "success";

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

//        return (T) (countryRepository.existsById(109) ? "true" : "false");
//        return (T) countryRepository.findAll();
//        ActorEntity actor = actorRepository.findById(200).get();
//        return (T) actor;
//        return (T) actorRepository.findById(200).get();
//        return (T) filmRepository.findById(5).get();
    }

    @Override
    @Transactional
    public void handleTransactional() {
        String postfix = " aa";
        actorService.saveActor("THORA" + postfix);
        cityService.saveCity("Ziguinchor" + postfix);
        countryService.saveCountry("Zambia" + postfix);
//        int a = 1/0;
    }

    @Override
    public <T> T handleLargeData() {
        List<SalariesEntity> salaryList = salariesRepository.findAll();
        List<EmployeeEntity> employeeData = employeeRepository.findAll();
        List<EmployeeEntity> result = employeeData.subList(0, 10);
        result.add(EmployeeEntity.builder()
                .empNo(9911)
                .build());

        long start = System.currentTimeMillis();
        System.out.println("start: " + start +  "********************************");

        Map<Integer, Integer> salaryMap = salaryList.stream()
                .filter(s -> s.getSalary() != null)
                .collect(Collectors.groupingBy(
                        SalariesEntity::getEmpNo,
                        Collectors.summingInt(SalariesEntity::getSalary)));

//        val salaryMap: Map<Int, Int> = salaryList
//            .filter { it.salary != null }
//            .groupBy({ it.empNo }, { it.salary!! })
//            .mapValues { it.value.sumOf { it } }

        for (EmployeeEntity s : result) {
            s.setSalary(salaryMap.get(s.getEmpNo()));
        }

        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start));
        System.out.println("end: " + end + " ********************************");
        return (T) result;
    }

}
