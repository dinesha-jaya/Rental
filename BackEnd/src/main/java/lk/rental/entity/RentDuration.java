package lk.rental.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class RentDuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentDurationId;
    private String rentDurationType;
    private double rate;
    private int freeKms;
    private double pricePerExtraKm;

    @OneToOne(cascade = {CascadeType.ALL})
    private Car car;
}
