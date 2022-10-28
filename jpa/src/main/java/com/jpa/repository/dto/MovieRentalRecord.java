package com.jpa.repository.dto;

import java.time.LocalDateTime;

public record MovieRentalRecord (
    String title,
    LocalDateTime rentalDate
){}
