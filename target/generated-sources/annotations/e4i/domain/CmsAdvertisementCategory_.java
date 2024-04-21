package e4i.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmsAdvertisementCategory.class)
public abstract class CmsAdvertisementCategory_ {

	public static volatile SingularAttribute<CmsAdvertisementCategory, Instant> createdAt;
	public static volatile SingularAttribute<CmsAdvertisementCategory, Document> image;
	public static volatile SingularAttribute<CmsAdvertisementCategory, PortalUser> createdBy;
	public static volatile SingularAttribute<CmsAdvertisementCategory, Instant> modifiedAt;
	public static volatile SingularAttribute<CmsAdvertisementCategory, PortalUser> modifiedBy;
	public static volatile SingularAttribute<CmsAdvertisementCategory, Long> id;
	public static volatile SingularAttribute<CmsAdvertisementCategory, AdvertisementCategory> advertisementCategory;

	public static final String CREATED_AT = "createdAt";
	public static final String IMAGE = "image";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_AT = "modifiedAt";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String ID = "id";
	public static final String ADVERTISEMENT_CATEGORY = "advertisementCategory";

}

