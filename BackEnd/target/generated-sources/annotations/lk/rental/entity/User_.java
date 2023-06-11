package lk.rental.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> userCategory;
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> username;

	public static final String PASSWORD = "password";
	public static final String USER_CATEGORY = "userCategory";
	public static final String USER_ID = "userId";
	public static final String USERNAME = "username";

}

