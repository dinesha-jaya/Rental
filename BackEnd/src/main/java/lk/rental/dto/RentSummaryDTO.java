package lk.rental.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentSummaryDTO {

    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String customerContactNo;

    private LocalDate rentStartDate;
    private LocalDate rentEndDate;
    private String rentDurationPlan;
    private String rentStatus;

    ArrayList<RentCarDTO> rentCarDTOs = new ArrayList<>();

}
