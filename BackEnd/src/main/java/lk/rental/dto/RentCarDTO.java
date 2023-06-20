package lk.rental.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentCarDTO {

    private String brand;
    private String fuelType;
    private int noOfPassengers;
    private String registrationNo;
    private String transmissionType;

    private double rate;
    private int freeKms;
    private double pricePerExtraKm;

    private String driverName;
    private String driverContactNo;

    private boolean lossDamageWaiverPaymentReceipt;
    private double lossDamageWaiverPaymentCharge;
    private double lossDamageWaiverPayment;
    private long meterStart;
    private long meterEnd;
    private double rateFee;
    private double driverFee;
    private double chargeForKms;
    private double amountForCarForTrip;
}
