package e4i.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RiProcurementProposal.class)
public abstract class RiProcurementProposal_ {

	public static volatile SingularAttribute<RiProcurementProposal, ZonedDateTime> date;
	public static volatile SingularAttribute<RiProcurementProposal, PortalUser> portalUser;
	public static volatile SingularAttribute<RiProcurementProposal, String> purpose;
	public static volatile SingularAttribute<RiProcurementProposal, String> technicalSpecification;
	public static volatile SingularAttribute<RiProcurementProposal, String> name;
	public static volatile SingularAttribute<RiProcurementProposal, Long> id;

	public static final String DATE = "date";
	public static final String PORTAL_USER = "portalUser";
	public static final String PURPOSE = "purpose";
	public static final String TECHNICAL_SPECIFICATION = "technicalSpecification";
	public static final String NAME = "name";
	public static final String ID = "id";

}

