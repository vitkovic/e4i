package e4i.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmsCarousel.class)
public abstract class CmsCarousel_ {

	public static volatile SingularAttribute<CmsCarousel, Instant> createdAt;
	public static volatile SingularAttribute<CmsCarousel, Document> image;
	public static volatile SingularAttribute<CmsCarousel, Long> ordinalNumber;
	public static volatile SingularAttribute<CmsCarousel, PortalUser> createdBy;
	public static volatile SingularAttribute<CmsCarousel, Instant> modifiedAt;
	public static volatile SingularAttribute<CmsCarousel, String> link;
	public static volatile SingularAttribute<CmsCarousel, PortalUser> modifiedBy;
	public static volatile SingularAttribute<CmsCarousel, Long> id;
	public static volatile SingularAttribute<CmsCarousel, String> title;
	public static volatile SingularAttribute<CmsCarousel, Boolean> newTab;

	public static final String CREATED_AT = "createdAt";
	public static final String IMAGE = "image";
	public static final String ORDINAL_NUMBER = "ordinalNumber";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_AT = "modifiedAt";
	public static final String LINK = "link";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String NEW_TAB = "newTab";

}

