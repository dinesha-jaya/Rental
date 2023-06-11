package lk.rental.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SearchCarDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private String carType;
}
