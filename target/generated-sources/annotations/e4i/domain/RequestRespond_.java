package e4i.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RequestRespond.class)
public abstract class RequestRespond_ {

	public static volatile SingularAttribute<RequestRespond, ZonedDateTime> date;
	public static volatile SingularAttribute<RequestRespond, RequestForService> requestForService;
	public static volatile SingularAttribute<RequestRespond, RiCalendar> calendar;
	public static volatile SingularAttribute<RequestRespond, ServiceOfferRespond> serviceOfferRespond;
	public static volatile SingularAttribute<RequestRespond, Double> price;
	public static volatile SingularAttribute<RequestRespond, RequestAnswer> requestAnswer;
	public static volatile SingularAttribute<RequestRespond, ZonedDateTime> startTime;
	public static volatile SingularAttribute<RequestRespond, Long> id;
	public static volatile SingularAttribute<RequestRespond, ZonedDateTime> endTime;
	public static volatile SingularAttribute<RequestRespond, String> message;

	public static final String DATE = "date";
	public static final String REQUEST_FOR_SERVICE = "requestForService";
	public static final String CALENDAR = "calendar";
	public static final String SERVICE_OFFER_RESPOND = "serviceOfferRespond";
	public static final String PRICE = "price";
	public static final String REQUEST_ANSWER = "requestAnswer";
	public static final String START_TIME = "startTime";
	public static final String ID = "id";
	public static final String END_TIME = "endTime";
	public static final String MESSAGE = "message";

}

