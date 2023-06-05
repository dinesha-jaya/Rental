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
    private double rate;
    private int noOfPassengers;
    private int freeKms;
    private double pricePerExtraKm;
}
