package e4i.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Company.class)
public abstract class Company_ {

	public static volatile SetAttribute<Company, Document> documents;
	public static volatile SingularAttribute<Company, Instant> modifiedAt;
	public static volatile SetAttribute<Company, Collaboration> collaborationOffers;
	public static volatile SingularAttribute<Company, String> about;
	public static volatile SetAttribute<Company, Thread> threadsReceivers;
	public static volatile SetAttribute<Company, AdvertisementSupporter> advertisementSupporters;
	public static volatile SetAttribute<Company, PortalUser> portalUsers;
	public static volatile SetAttribute<Company, Badge> badges;
	public static volatile SingularAttribute<Company, Instant> createdAt;
	public static volatile SingularAttribute<Company, Long> rsnisId;
	public static volatile SetAttribute<Company, Advertisement> advertisements;
	public static volatile SingularAttribute<Company, Long> aprId;
	public static volatile SingularAttribute<Company, PortalUser> createdBy;
	public static volatile SetAttribute<Company, Thread> threadsSenders;
	public static volatile SingularAttribute<Company, String> name;
	public static volatile SingularAttribute<Company, Document> logo;
	public static volatile SingularAttribute<Company, PortalUser> modifiedBy;
	public static volatile SingularAttribute<Company, Long> id;
	public static volatile SingularAttribute<Company, String> fields;
	public static volatile SetAttribute<Company, Collaboration> collaborationRequests;

	public static final String DOCUMENTS = "documents";
	public static final String MODIFIED_AT = "modifiedAt";
	public static final String COLLABORATION_OFFERS = "collaborationOffers";
	public static final String ABOUT = "about";
	public static final String THREADS_RECEIVERS = "threadsReceivers";
	public static final String ADVERTISEMENT_SUPPORTERS = "advertisementSupporters";
	public static final String PORTAL_USERS = "portalUsers";
	public static final String BADGES = "badges";
	public static final String CREATED_AT = "createdAt";
	public static final String RSNIS_ID = "rsnisId";
	public static final String ADVERTISEMENTS = "advertisements";
	public static final String APR_ID = "aprId";
	public static final String CREATED_BY = "createdBy";
	public static final String THREADS_SENDERS = "threadsSenders";
	public static final String NAME = "name";
	public static final String LOGO = "logo";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String ID = "id";
	public static final String FIELDS = "fields";
	public static final String COLLABORATION_REQUESTS = "collaborationRequests";

}

