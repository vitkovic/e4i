package e4i.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmsQuestion.class)
public abstract class CmsQuestion_ {

	public static volatile SingularAttribute<CmsQuestion, Instant> createdAt;
	public static volatile SingularAttribute<CmsQuestion, String> question;
	public static volatile SingularAttribute<CmsQuestion, String> answer;
	public static volatile SingularAttribute<CmsQuestion, PortalUser> createdBy;
	public static volatile SingularAttribute<CmsQuestion, Instant> modifiedAt;
	public static volatile SingularAttribute<CmsQuestion, PortalUser> modifiedBy;
	public static volatile SingularAttribute<CmsQuestion, Long> id;

	public static final String CREATED_AT = "createdAt";
	public static final String QUESTION = "question";
	public static final String ANSWER = "answer";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_AT = "modifiedAt";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String ID = "id";

}

