package e4i.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ServiceOfferRespond.class)
public abstract class ServiceOfferRespond_ {

	public static volatile SingularAttribute<ServiceOfferRespond, ZonedDateTime> date;
	public static volatile SingularAttribute<ServiceOfferRespond, RequestRespond> requestRespond;
	public static volatile SingularAttribute<ServiceOfferRespond, AnswerToOffer> offerAnswer;
	public static volatile SingularAttribute<ServiceOfferRespond, String> description;
	public static volatile SingularAttribute<ServiceOfferRespond, Long> id;

	public static final String DATE = "date";
	public static final String REQUEST_RESPOND = "requestRespond";
	public static final String OFFER_ANSWER = "offerAnswer";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

