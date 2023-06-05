package lk.rental.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    private String firstName;
    private String lastName;
    private String idType;
    private boolean idImage;

//    @Column(unique = true)
    private String email;
    private String address;
    private String contactNo;

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
    private List<Rental> rentals = new ArrayList<>();


}
