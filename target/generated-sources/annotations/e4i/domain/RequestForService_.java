package e4i.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RequestForService.class)
public abstract class RequestForService_ {

	public static volatile SingularAttribute<RequestForService, ZonedDateTime> date;
	public static volatile SingularAttribute<RequestForService, Integer> rating;
	public static volatile SingularAttribute<RequestForService, String> description;
	public static volatile SingularAttribute<RequestForService, RiService> services;
	public static volatile SingularAttribute<RequestForService, String> title;
	public static volatile SingularAttribute<RequestForService, PortalUser> users;
	public static volatile SingularAttribute<RequestForService, PortalUserOrganization> organizations;
	public static volatile SingularAttribute<RequestForService, ZonedDateTime> startTime;
	public static volatile SingularAttribute<RequestForService, Long> id;
	public static volatile SingularAttribute<RequestForService, ZonedDateTime> endTime;
	public static volatile SingularAttribute<RequestForService, RequestState> state;
	public static volatile SingularAttribute<RequestForService, ZonedDateTime> deadline;
	public static volatile SingularAttribute<RequestForService, RequestRespond> respond;

	public static final String DATE = "date";
	public static final String RATING = "rating";
	public static final String DESCRIPTION = "description";
	public static final String SERVICES = "services";
	public static final String TITLE = "title";
	public static final String USERS = "users";
	public static final String ORGANIZATIONS = "organizations";
	public static final String START_TIME = "startTime";
	public static final String ID = "id";
	public static final String END_TIME = "endTime";
	public static final String STATE = "state";
	public static final String DEADLINE = "deadline";
	public static final String RESPOND = "respond";

}

