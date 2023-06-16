package lk.rental.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
@Entity
public class RentHasCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentHasCarId;
    private long meterStart;
    private long meterEnd;
    private boolean driverOption;
    private boolean lossDamageWaiverPaymentReceipt;
    private double lossDamageWaiverPaymentCharge;
    private double lossDamageWaiverPayment;
    private double driverFee;
    private double rateFee;
    private double chargeForKms;
    private double amountPerCarPerTrip;

    @OneToOne
    private Driver driver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rentId")
    private Rent rent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carId")
    private Car car;

}