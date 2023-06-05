package lk.rental.entity;

import lk.rental.dto.PricingDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@NamedNativeQuery(name = "Car.findPricingDto",
        query = "SELECT DISTINCT brand, rentDurationType, rate, noOfPassengers, freeKms, pricePerExtraKm FROM (SELECT * FROM car INNER JOIN rentDuration on carId = car_carId WHERE type = ?1) as pricing",
        resultSetMapping = "Mapping.PricingDTO")
@SqlResultSetMapping(name = "Mapping.PricingDTO",
        classes = @ConstructorResult(targetClass = PricingDTO.class,
                columns = {@ColumnResult(name = "brand"),
                        @ColumnResult(name = "rentDurationType"),
                        @ColumnResult(name = "rate"),
                        @ColumnResult(name = "noOfPassengers"),
                        @ColumnResult(name = "freeKms"),
                        @ColumnResult(name = "pricePerExtraKm")}))
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long carId;
    private String type;
    private String brand;
    private String color;
    private String fuelType;
    private String registrationNo;
    private int noOfPassengers;
    private String transmissionType;
    private String status;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<RentalHasCar> rentalHasCars = new ArrayList<>();
}
