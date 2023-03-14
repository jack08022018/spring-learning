package com.validation.dto;

import com.validation.customValidate.AddressConstraint;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AddressConstraint(message = "Wrong address")
public class Address {
    @NotNull(message = "zipCode is not null")
    @Size(max = 10)
    private String zipCode;

    @NotNull(message = "city is not null")
    private String city;

    @Valid
    @NotNull(message = "country is not null")
    private Country country;

    // Getters and setters
}
