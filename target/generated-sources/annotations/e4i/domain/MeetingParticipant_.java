package e4i.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MeetingParticipant.class)
public abstract class MeetingParticipant_ {

	public static volatile SingularAttribute<MeetingParticipant, PortalUser> portalUser;
	public static volatile SingularAttribute<MeetingParticipant, Boolean> hasAccepted;
	public static volatile SingularAttribute<MeetingParticipant, Long> id;
	public static volatile SingularAttribute<MeetingParticipant, Meeting> meeting;

	public static final String PORTAL_USER = "portalUser";
	public static final String HAS_ACCEPTED = "hasAccepted";
	public static final String ID = "id";
	public static final String MEETING = "meeting";

}

