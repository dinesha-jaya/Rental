package lk.rental.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public enum DriverFee {
    DRIVER_FEE (1000.0);

    private double fee;
}
