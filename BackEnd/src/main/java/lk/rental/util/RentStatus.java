package lk.rental.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public enum RentStatus {

    PENDING ("pending"),
    CURRENT ("current"),
    END ("end"),
    PAID ("paid");

    public String status;

}
