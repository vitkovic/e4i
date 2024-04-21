package e4i.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RiCalendar.class)
public abstract class RiCalendar_ {

	public static volatile SingularAttribute<RiCalendar, RequestRespond> requestRespond;
	public static volatile SingularAttribute<RiCalendar, String> calComment;
	public static volatile SingularAttribute<RiCalendar, String> calNote;
	public static volatile SingularAttribute<RiCalendar, RiServiceStatus> serviceStatus;
	public static volatile SingularAttribute<RiCalendar, RiService> service;
	public static volatile SingularAttribute<RiCalendar, ZonedDateTime> startTime;
	public static volatile SingularAttribute<RiCalendar, Long> id;
	public static volatile SingularAttribute<RiCalendar, ZonedDateTime> endTime;
	public static volatile SingularAttribute<RiCalendar, ResearchInfrastructure> researchInfrastructure;
	public static volatile SingularAttribute<RiCalendar, String> calRating;
	public static volatile SingularAttribute<RiCalendar, PortalUser> portalUsers;

	public static final String REQUEST_RESPOND = "requestRespond";
	public static final String CAL_COMMENT = "calComment";
	public static final String CAL_NOTE = "calNote";
	public static final String SERVICE_STATUS = "serviceStatus";
	public static final String SERVICE = "service";
	public static final String START_TIME = "startTime";
	public static final String ID = "id";
	public static final String END_TIME = "endTime";
	public static final String RESEARCH_INFRASTRUCTURE = "researchInfrastructure";
	public static final String CAL_RATING = "calRating";
	public static final String PORTAL_USERS = "portalUsers";

}

