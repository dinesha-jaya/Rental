package lk.rental.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, String> firstName;
	public static volatile SingularAttribute<Customer, String> lastName;
	public static volatile SingularAttribute<Customer, String> idType;
	public static volatile SingularAttribute<Customer, String> address;
	public static volatile SingularAttribute<Customer, Long> customerId;
	public static volatile SingularAttribute<Customer, Boolean> idImage;
	public static volatile ListAttribute<Customer, Rent> rents;
	public static volatile SingularAttribute<Customer, User> user;
	public static volatile SingularAttribute<Customer, String> email;
	public static volatile SingularAttribute<Customer, String> contactNo;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String ID_TYPE = "idType";
	public static final String ADDRESS = "address";
	public static final String CUSTOMER_ID = "customerId";
	public static final String ID_IMAGE = "idImage";
	public static final String RENTS = "rents";
	public static final String USER = "user";
	public static final String EMAIL = "email";
	public static final String CONTACT_NO = "contactNo";

}

