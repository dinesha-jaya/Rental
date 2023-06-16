package lk.rental.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RentDuration.class)
public abstract class RentDuration_ {

	public static volatile SingularAttribute<RentDuration, Integer> freeKmsPerType;
	public static volatile SingularAttribute<RentDuration, Car> car;
	public static volatile SingularAttribute<RentDuration, String> rentDurationType;
	public static volatile SingularAttribute<RentDuration, Double> ratePerType;
	public static volatile SingularAttribute<RentDuration, Long> rentDurationId;
	public static volatile SingularAttribute<RentDuration, Double> pricePerExtraKm;

	public static final String FREE_KMS_PER_TYPE = "freeKmsPerType";
	public static final String CAR = "car";
	public static final String RENT_DURATION_TYPE = "rentDurationType";
	public static final String RATE_PER_TYPE = "ratePerType";
	public static final String RENT_DURATION_ID = "rentDurationId";
	public static final String PRICE_PER_EXTRA_KM = "pricePerExtraKm";

}

