package lk.rental.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentDTO {

    private long rentId;
    private String rentDurationPlan;
    private LocalDate startDate;
    private LocalDate endDate;
    private double amount;
    private String status;
    private String remarks;


    private ArrayList<CarDTO> carList;


}
