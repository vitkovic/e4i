package e4i.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ServiceProposalsLikes.class)
public abstract class ServiceProposalsLikes_ {

	public static volatile SingularAttribute<ServiceProposalsLikes, ServiceProposal> proposal;
	public static volatile SingularAttribute<ServiceProposalsLikes, PortalUser> portalUser;
	public static volatile SingularAttribute<ServiceProposalsLikes, Integer> ndislike;
	public static volatile SingularAttribute<ServiceProposalsLikes, Integer> nlike;
	public static volatile SingularAttribute<ServiceProposalsLikes, Long> id;

	public static final String PROPOSAL = "proposal";
	public static final String PORTAL_USER = "portalUser";
	public static final String NDISLIKE = "ndislike";
	public static final String NLIKE = "nlike";
	public static final String ID = "id";

}

