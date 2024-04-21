package e4i.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Meeting.class)
public abstract class Meeting_ {

	public static volatile SingularAttribute<Meeting, Instant> datetime;
	public static volatile SingularAttribute<Meeting, String> notes;
	public static volatile SingularAttribute<Meeting, Advertisement> advertisement;
	public static volatile SingularAttribute<Meeting, String> description;
	public static volatile SingularAttribute<Meeting, String> comment;
	public static volatile SingularAttribute<Meeting, Long> id;
	public static volatile SingularAttribute<Meeting, String> title;
	public static volatile SingularAttribute<Meeting, MeetingType> type;
	public static volatile SingularAttribute<Meeting, Boolean> isAcepted;
	public static volatile SetAttribute<Meeting, MeetingParticipant> meetingParticipants;
	public static volatile SingularAttribute<Meeting, PortalUser> portalUserOrganizer;

	public static final String DATETIME = "datetime";
	public static final String NOTES = "notes";
	public static final String ADVERTISEMENT = "advertisement";
	public static final String DESCRIPTION = "description";
	public static final String COMMENT = "comment";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String IS_ACEPTED = "isAcepted";
	public static final String MEETING_PARTICIPANTS = "meetingParticipants";
	public static final String PORTAL_USER_ORGANIZER = "portalUserOrganizer";

}

