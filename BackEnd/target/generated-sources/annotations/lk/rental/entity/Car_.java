package lk.rental.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Car.class)
public abstract class Car_ {

	public static volatile SingularAttribute<Car, String> color;
	public static volatile SingularAttribute<Car, String> fuelType;
	public static volatile SingularAttribute<Car, String> registrationNo;
	public static volatile SingularAttribute<Car, String> transmissionType;
	public static volatile SingularAttribute<Car, Integer> noOfPassengers;
	public static volatile SingularAttribute<Car, String> type;
	public static volatile ListAttribute<Car, RentHasCar> rentHasCars;
	public static volatile SingularAttribute<Car, String> brand;
	public static volatile SingularAttribute<Car, Long> carId;
	public static volatile SingularAttribute<Car, String> status;

	public static final String COLOR = "color";
	public static final String FUEL_TYPE = "fuelType";
	public static final String REGISTRATION_NO = "registrationNo";
	public static final String TRANSMISSION_TYPE = "transmissionType";
	public static final String NO_OF_PASSENGERS = "noOfPassengers";
	public static final String TYPE = "type";
	public static final String RENT_HAS_CARS = "rentHasCars";
	public static final String BRAND = "brand";
	public static final String CAR_ID = "carId";
	public static final String STATUS = "status";

}

