package lk.rental.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentEndCarDTO {

    private String registrationNo;
    private double lossDamageWaiverPaymentCharge;
    private long meterEnd;

}
