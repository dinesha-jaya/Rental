package lk.rental.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public enum CarType {

    GENERAL ("general"),
    PREMIUM ("premium"),
    LUXURY ("luxury");

    public String carType;
}
