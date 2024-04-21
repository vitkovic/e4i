package e4i.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RiResearchOrganization.class)
public abstract class RiResearchOrganization_ {

	public static volatile SingularAttribute<RiResearchOrganization, String> descriptionSrCyr;
	public static volatile SetAttribute<RiResearchOrganization, ScientificSubdomain> subdomains;
	public static volatile SingularAttribute<RiResearchOrganization, PortalUserOrganization> puOrganization;
	public static volatile SingularAttribute<RiResearchOrganization, PortalUser> manager;
	public static volatile SingularAttribute<RiResearchOrganization, String> decsriptionEn;
	public static volatile SingularAttribute<RiResearchOrganization, String> name;
	public static volatile SingularAttribute<RiResearchOrganization, RipoActivityStatus> activityStatus;
	public static volatile SingularAttribute<RiResearchOrganization, String> logo;
	public static volatile SingularAttribute<RiResearchOrganization, PortalUser> contactPerson;
	public static volatile SingularAttribute<RiResearchOrganization, Long> id;
	public static volatile SetAttribute<RiResearchOrganization, ResearchInfrastructure> infrastructures;
	public static volatile SingularAttribute<RiResearchOrganization, String> descriptionSr;

	public static final String DESCRIPTION_SR_CYR = "descriptionSrCyr";
	public static final String SUBDOMAINS = "subdomains";
	public static final String PU_ORGANIZATION = "puOrganization";
	public static final String MANAGER = "manager";
	public static final String DECSRIPTION_EN = "decsriptionEn";
	public static final String NAME = "name";
	public static final String ACTIVITY_STATUS = "activityStatus";
	public static final String LOGO = "logo";
	public static final String CONTACT_PERSON = "contactPerson";
	public static final String ID = "id";
	public static final String INFRASTRUCTURES = "infrastructures";
	public static final String DESCRIPTION_SR = "descriptionSr";

}

