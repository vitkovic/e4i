package e4i.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PortalUserRole.class)
public abstract class PortalUserRole_ {

	public static volatile SingularAttribute<PortalUserRole, String> role;
	public static volatile SingularAttribute<PortalUserRole, String> fullName;
	public static volatile SingularAttribute<PortalUserRole, Long> id;
	public static volatile SingularAttribute<PortalUserRole, String> abbreviation;
	public static volatile SetAttribute<PortalUserRole, PortalUser> users;

	public static final String ROLE = "role";
	public static final String FULL_NAME = "fullName";
	public static final String ID = "id";
	public static final String ABBREVIATION = "abbreviation";
	public static final String USERS = "users";

}

