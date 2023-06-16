package lk.rental.util;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public enum LossDamageWaiverPayment {

    LOSS_DAMAGE_WAIVER_PAYMENT_GENERAL (10000.0),
    LOSS_DAMAGE_WAIVER_PAYMENT_PREMIUM (15000.0),
    LOSS_DAMAGE_WAIVER_PAYMENT_LUXURY (20000.0);

    private double payment;
}
