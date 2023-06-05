package lk.rental.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentalId;
    private String rentDurationPlan;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualEndDate;
    private double amount;
    private String status;
    private String remarks;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private List<RentalHasCar> rentalHasCars = new ArrayList<>();

    public void addRentalHasCar(RentalHasCar rentalHasCar) {
        this.rentalHasCars.add(rentalHasCar);
    }
}
