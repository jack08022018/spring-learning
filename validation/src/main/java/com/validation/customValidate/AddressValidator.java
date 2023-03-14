package com.validation.customValidate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.validation.dto.Address;
import com.validation.repository.CityRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class AddressValidator implements ConstraintValidator<AddressConstraint, Address> {
    final CityRepository cityRepository;
    final ObjectMapper customObjectMapper;

    public void initialize(AddressConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Address address, ConstraintValidatorContext constraintValidatorContext) {
        var city = cityRepository.findById(1).get();
        System.out.println("aaa: " + city.getCity());
        var country = address.getCountry();
        if (country == null || country.getIso2() == null || address.getZipCode() == null) {
            return true;
        }
        switch (country.getIso2()) {
            case "FR":
                return address.getZipCode().equals("001");
            case "GR":
                return address.getZipCode().equals("002");
            default:
                return true;
        }
    }
}
