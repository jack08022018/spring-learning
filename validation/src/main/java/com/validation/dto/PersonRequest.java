package com.validation.dto;

import com.validation.customValidate.CapitalizedConstraint;
import com.validation.customValidate.DateTimeConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
    @CapitalizedConstraint(message = "Chữ đầu tiên phải được in hoa!")
    @NotNull(message = "Tên bị null!")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message = "Tên chứa kí tự đặc biệt!")
    @Length(min = 3, max = 200, message = "Tên phải từ 3 đến 200 kí tự!")
    private String name;

//    \d{4}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])
//    ([01]\d|2[0-3])[0-5]\d{2}
//    (([0-1]?[0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]
    @DateTimeConstraint(message = "Datetime format yyyyMMddHHmmss")
    private String dateTime;

    @Min(value = 0, message = "Tuổi không được bé hơn 0!")
    @Max(value = 200, message = "Tuổi không được lớn hơn 200!")
    private Long age;

    @DecimalMin(value = "0.0", inclusive = false, message = "Chiều cao phải là số dương!")
    @DecimalMax(value = "300.5", message = "Chiều cao không được cao quá 300.5 cm!")
    @Digits(integer = 3, fraction = 2, message = "Chiều cao không khớp định dạng tối đa 3 số phần nguyên và 2 số phần thập phân!")
    private Double height;

    @Size(min = 2, max = 100, message = "Phải có ít nhất 2 sở thích!")
    private List<String> hobbies;
}
