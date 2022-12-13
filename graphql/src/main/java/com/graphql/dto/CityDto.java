package com.graphql.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
    @Pattern(regexp = "[a-zA-Z() ]+", message = "cityName chứa kí tự đặc biệt!")
    private String cityName;

//    @NotNull(message = "cityId is not null")
    @Min(value = 10, message = "cityId >= 10")
    private int cityId;
}
