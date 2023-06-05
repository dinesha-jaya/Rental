package lk.rental.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentalHasCarDTO {

//    private long rentalHasCarId;
    private int meterStart;
    private int meterEnd;
    private boolean driverOption;
    private boolean lossDamageWaiverPaymentReceipt;
    private double lossDamageWaiverPaymentCharge;
}
