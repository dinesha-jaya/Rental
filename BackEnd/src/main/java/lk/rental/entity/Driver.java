package lk.rental.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long driverId;
    private String name;
    private boolean license;
    private String email;
    private String contactNo;
    private String status;

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;
}
