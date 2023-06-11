package lk.rental.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
//@NamedNativeQuery(name = "Rent.findRentedCarDetail",
//        query = "SELECT re.startDate as startDate, re.endDate as endDate, rhc.carId as carId FROM rent re INNER JOIN rentHasCar rhc ON re.rentId = rhc.rentId",
//        resultSetMapping = "Mapping.RentedCarDetailDTO")
//@SqlResultSetMapping(name = "Mapping.RentedCarDetailDTO",
//        classes = @ConstructorResult(targetClass = RentedCarDetailDTO.class,
//                columns = {@ColumnResult(name = "startDate"),
//                        @ColumnResult(name = "endDate"),
//                        @ColumnResult(name = "carId")}))
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentId;
    private String rentDurationPlan;
    private Date startDate;
    private Date endDate;
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
