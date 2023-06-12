package lk.rental.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentStartCarDTO {

    private String registrationNo;
    private boolean lossDamageWaiverPaymentReceipt;
    private long meterStart;
}
