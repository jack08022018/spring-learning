package com.graphql.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {
//    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message = "countryName chứa kí tự đặc biệt!")
    private String countryName;

//    @NotNull(message = "countryId is not null")
    private int countryId;
}
