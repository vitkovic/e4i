package e4i.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmsTestimonials.class)
public abstract class CmsTestimonials_ {

	public static volatile SingularAttribute<CmsTestimonials, Instant> createdAt;
	public static volatile SingularAttribute<CmsTestimonials, Document> image;
	public static volatile SingularAttribute<CmsTestimonials, PortalUser> createdBy;
	public static volatile SingularAttribute<CmsTestimonials, Instant> modifiedAt;
	public static volatile SingularAttribute<CmsTestimonials, PortalUser> modifiedBy;
	public static volatile SingularAttribute<CmsTestimonials, Long> id;
	public static volatile SingularAttribute<CmsTestimonials, String> title;
	public static volatile SingularAttribute<CmsTestimonials, String> content;

	public static final String CREATED_AT = "createdAt";
	public static final String IMAGE = "image";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_AT = "modifiedAt";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";

}

