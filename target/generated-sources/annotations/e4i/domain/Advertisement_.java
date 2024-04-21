package e4i.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Advertisement.class)
public abstract class Advertisement_ {

	public static volatile SetAttribute<Advertisement, Collaboration> collaborations;
	public static volatile SetAttribute<Advertisement, Document> documents;
	public static volatile SingularAttribute<Advertisement, Instant> modifiedAt;
	public static volatile SingularAttribute<Advertisement, AdvertisementKind> kind;
	public static volatile SingularAttribute<Advertisement, String> description;
	public static volatile SetAttribute<Advertisement, Thread> threads;
	public static volatile SingularAttribute<Advertisement, String> title;
	public static volatile SingularAttribute<Advertisement, AdvertisementType> type;
	public static volatile SetAttribute<Advertisement, AdvertisementSupporter> advertisementSupporters;
	public static volatile SingularAttribute<Advertisement, Instant> activationDatetime;
	public static volatile SingularAttribute<Advertisement, AdvertisementDuration> duration;
	public static volatile SingularAttribute<Advertisement, Instant> createdAt;
	public static volatile SingularAttribute<Advertisement, PortalUser> createdBy;
	public static volatile SingularAttribute<Advertisement, AdvertisementSubsubcategory> subsubcategory;
	public static volatile SetAttribute<Advertisement, Meeting> meetings;
	public static volatile SingularAttribute<Advertisement, PortalUser> modifiedBy;
	public static volatile SingularAttribute<Advertisement, Company> company;
	public static volatile SingularAttribute<Advertisement, Long> id;
	public static volatile SingularAttribute<Advertisement, String> conditions;
	public static volatile SingularAttribute<Advertisement, Long> budget;
	public static volatile SingularAttribute<Advertisement, AdvertisementStatus> status;

	public static final String COLLABORATIONS = "collaborations";
	public static final String DOCUMENTS = "documents";
	public static final String MODIFIED_AT = "modifiedAt";
	public static final String KIND = "kind";
	public static final String DESCRIPTION = "description";
	public static final String THREADS = "threads";
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String ADVERTISEMENT_SUPPORTERS = "advertisementSupporters";
	public static final String ACTIVATION_DATETIME = "activationDatetime";
	public static final String DURATION = "duration";
	public static final String CREATED_AT = "createdAt";
	public static final String CREATED_BY = "createdBy";
	public static final String SUBSUBCATEGORY = "subsubcategory";
	public static final String MEETINGS = "meetings";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String COMPANY = "company";
	public static final String ID = "id";
	public static final String CONDITIONS = "conditions";
	public static final String BUDGET = "budget";
	public static final String STATUS = "status";

}

