package lk.rental.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CarDTO {

//    private long carId;
    private String type;
    private String brand;
    private String color;
    private String fuelType;
    private String registrationNo;
    private int noOfPassengers;
    private String transmissionType;
    private String status;
}
