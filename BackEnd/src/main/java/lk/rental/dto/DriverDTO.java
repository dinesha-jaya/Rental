package lk.rental.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DriverDTO {

//    private long driverId;
    private String name;
    private boolean license;
    private String contactNo;
    private String status;
}
