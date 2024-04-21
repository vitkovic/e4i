package e4i.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ServiceProposal.class)
public abstract class ServiceProposal_ {

	public static volatile SingularAttribute<ServiceProposal, ZonedDateTime> date;
	public static volatile SingularAttribute<ServiceProposal, PortalUser> portalUser;
	public static volatile SingularAttribute<ServiceProposal, String> purpose;
	public static volatile SingularAttribute<ServiceProposal, String> name;
	public static volatile SingularAttribute<ServiceProposal, String> description;
	public static volatile SingularAttribute<ServiceProposal, Long> id;

	public static final String DATE = "date";
	public static final String PORTAL_USER = "portalUser";
	public static final String PURPOSE = "purpose";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

