package lk.rental.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RentHasCar.class)
public abstract class RentHasCar_ {

	public static volatile SingularAttribute<RentHasCar, Double> lossDamageWaiverPaymentCharge;
	public static volatile SingularAttribute<RentHasCar, Driver> driver;
	public static volatile SingularAttribute<RentHasCar, Car> car;
	public static volatile SingularAttribute<RentHasCar, Long> meterEnd;
	public static volatile SingularAttribute<RentHasCar, Long> meterStart;
	public static volatile SingularAttribute<RentHasCar, Boolean> lossDamageWaiverPaymentReceipt;
	public static volatile SingularAttribute<RentHasCar, Rent> rent;
	public static volatile SingularAttribute<RentHasCar, Long> rentHasCarId;
	public static volatile SingularAttribute<RentHasCar, Boolean> driverOption;

	public static final String LOSS_DAMAGE_WAIVER_PAYMENT_CHARGE = "lossDamageWaiverPaymentCharge";
	public static final String DRIVER = "driver";
	public static final String CAR = "car";
	public static final String METER_END = "meterEnd";
	public static final String METER_START = "meterStart";
	public static final String LOSS_DAMAGE_WAIVER_PAYMENT_RECEIPT = "lossDamageWaiverPaymentReceipt";
	public static final String RENT = "rent";
	public static final String RENT_HAS_CAR_ID = "rentHasCarId";
	public static final String DRIVER_OPTION = "driverOption";

}

