package lk.rental.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CarSearchDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private String carType;
}
