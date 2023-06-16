package lk.rental.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentEndDTO {

    private long rentId;
    private LocalDate rentTerminatedDate;
    private String status;

    private ArrayList<RentEndCarDTO> rentEndCarList;
}
