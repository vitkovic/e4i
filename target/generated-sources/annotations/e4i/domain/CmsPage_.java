package e4i.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmsPage.class)
public abstract class CmsPage_ {

	public static volatile SingularAttribute<CmsPage, Instant> createdAt;
	public static volatile SetAttribute<CmsPage, Document> documents;
	public static volatile SingularAttribute<CmsPage, PortalUser> createdBy;
	public static volatile SingularAttribute<CmsPage, Instant> modifiedAt;
	public static volatile SingularAttribute<CmsPage, PortalUser> modifiedBy;
	public static volatile SingularAttribute<CmsPage, Long> id;
	public static volatile SingularAttribute<CmsPage, String> title;
	public static volatile SingularAttribute<CmsPage, CmsPageType> type;
	public static volatile SingularAttribute<CmsPage, String> content;

	public static final String CREATED_AT = "createdAt";
	public static final String DOCUMENTS = "documents";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_AT = "modifiedAt";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String CONTENT = "content";

}

