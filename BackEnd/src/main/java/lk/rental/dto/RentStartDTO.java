package lk.rental.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentStartDTO {

    private String rentDurationPlan;
    private LocalDate startDate;
    private LocalDate endDate;
    private String username;

    ArrayList<CarDriverDTO> carDriverList;

}
