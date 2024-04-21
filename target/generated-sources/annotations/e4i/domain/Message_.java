package e4i.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Message.class)
public abstract class Message_ {

	public static volatile SingularAttribute<Message, Instant> datetime;
	public static volatile SingularAttribute<Message, Boolean> isDeletedSender;
	public static volatile SingularAttribute<Message, Boolean> isRead;
	public static volatile SingularAttribute<Message, PortalUser> portalUserSender;
	public static volatile SingularAttribute<Message, Boolean> isDeletedReceiver;
	public static volatile SingularAttribute<Message, PortalUser> portalUserReceiver;
	public static volatile SingularAttribute<Message, Long> id;
	public static volatile SingularAttribute<Message, Thread> thread;
	public static volatile SingularAttribute<Message, String> content;

	public static final String DATETIME = "datetime";
	public static final String IS_DELETED_SENDER = "isDeletedSender";
	public static final String IS_READ = "isRead";
	public static final String PORTAL_USER_SENDER = "portalUserSender";
	public static final String IS_DELETED_RECEIVER = "isDeletedReceiver";
	public static final String PORTAL_USER_RECEIVER = "portalUserReceiver";
	public static final String ID = "id";
	public static final String THREAD = "thread";
	public static final String CONTENT = "content";

}

