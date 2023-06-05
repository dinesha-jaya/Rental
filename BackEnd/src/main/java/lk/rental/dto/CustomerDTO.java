package lk.rental.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {

//    private long customerId;
    private String firstName;
    private String lastName;
    private String idType;
    private boolean idImage;
    private String email;
    private String address;
    private String contactNo;
//    private long userId;
}
