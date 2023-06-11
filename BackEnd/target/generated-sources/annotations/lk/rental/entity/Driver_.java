package lk.rental.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Driver.class)
public abstract class Driver_ {

	public static volatile SingularAttribute<Driver, Boolean> license;
	public static volatile SingularAttribute<Driver, Long> driverId;
	public static volatile SingularAttribute<Driver, String> name;
	public static volatile SingularAttribute<Driver, User> user;
	public static volatile SingularAttribute<Driver, String> email;
	public static volatile SingularAttribute<Driver, String> contactNo;
	public static volatile SingularAttribute<Driver, String> status;

	public static final String LICENSE = "license";
	public static final String DRIVER_ID = "driverId";
	public static final String NAME = "name";
	public static final String USER = "user";
	public static final String EMAIL = "email";
	public static final String CONTACT_NO = "contactNo";
	public static final String STATUS = "status";

}

