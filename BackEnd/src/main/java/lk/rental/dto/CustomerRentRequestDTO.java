package lk.rental.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerRentRequestDTO {

    private String email;
    private long rentId;
}
