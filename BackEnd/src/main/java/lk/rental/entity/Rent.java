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
//@ToString
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentId;
    private String rentDurationPlan;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualEndDate;
    private double amount;
    private String status;
    private String remarks;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "rent", cascade = CascadeType.ALL)
    private List<RentHasCar> rentHasCars = new ArrayList<>();

    public void addRentalHasCar(RentHasCar rentHasCar) {
        this.rentHasCars.add(rentHasCar);
    }
}
