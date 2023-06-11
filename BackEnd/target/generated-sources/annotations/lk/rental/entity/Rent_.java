package lk.rental.entity;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rent.class)
public abstract class Rent_ {

	public static volatile SingularAttribute<Rent, Double> amount;
	public static volatile SingularAttribute<Rent, Date> endDate;
	public static volatile SingularAttribute<Rent, String> rentDurationPlan;
	public static volatile ListAttribute<Rent, RentHasCar> rentHasCars;
	public static volatile SingularAttribute<Rent, Date> startDate;
	public static volatile SingularAttribute<Rent, String> remarks;
	public static volatile SingularAttribute<Rent, Long> rentId;
	public static volatile SingularAttribute<Rent, String> status;
	public static volatile SingularAttribute<Rent, Customer> customer;

	public static final String AMOUNT = "amount";
	public static final String END_DATE = "endDate";
	public static final String RENT_DURATION_PLAN = "rentDurationPlan";
	public static final String RENT_HAS_CARS = "rentHasCars";
	public static final String START_DATE = "startDate";
	public static final String REMARKS = "remarks";
	public static final String RENT_ID = "rentId";
	public static final String STATUS = "status";
	public static final String CUSTOMER = "customer";

}

