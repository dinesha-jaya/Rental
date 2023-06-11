package lk.rental.dto;

import lombok.*;

import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class RentedCarDetailDTO {

    private Date startDate;
    private Date endDate;
    private long carId;
}
