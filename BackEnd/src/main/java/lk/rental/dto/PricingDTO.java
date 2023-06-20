package lk.rental.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PricingDTO {

    private String brand;
    private String rentDurationType;
    private double ratePerType;
    private int noOfPassengers;
    private int freeKmsPerType;
    private double pricePerExtraKm;
}
