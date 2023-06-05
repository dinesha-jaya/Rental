package lk.rental.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class RentalHasCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentalHasCarId;
    private int meterStart;
    private int meterEnd;
    private boolean driverOption;
    private boolean lossDamageWaiverPaymentReceipt;
    private double lossDamageWaiverPaymentCharge;

    @OneToOne
    private Driver driver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rentalId")
    private Rental rental;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carId")
    private Car car;

}