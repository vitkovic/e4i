package e4i.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PortalUserOrganization.class)
public abstract class PortalUserOrganization_ {

	public static volatile SingularAttribute<PortalUserOrganization, ResearchInstitution> nioInstitution;
	public static volatile SingularAttribute<PortalUserOrganization, String> country;
	public static volatile SingularAttribute<PortalUserOrganization, String> address;
	public static volatile SingularAttribute<PortalUserOrganization, String> keywords;
	public static volatile SingularAttribute<PortalUserOrganization, String> city;
	public static volatile SingularAttribute<PortalUserOrganization, String> webAddress;
	public static volatile SingularAttribute<PortalUserOrganization, String> postalCode;
	public static volatile SingularAttribute<PortalUserOrganization, String> pib;
	public static volatile SingularAttribute<PortalUserOrganization, PuoLegalStatus> legalStatus;
	public static volatile SingularAttribute<PortalUserOrganization, String> legalNameSrCyr;
	public static volatile SetAttribute<PortalUserOrganization, RequestForService> requests;
	public static volatile SetAttribute<PortalUserOrganization, PortalUser> portalUsers;
	public static volatile SingularAttribute<PortalUserOrganization, RiResearchOrganization> researchOrganization;
	public static volatile SingularAttribute<PortalUserOrganization, String> keywordsCyr;
	public static volatile SingularAttribute<PortalUserOrganization, String> registrationNumber;
	public static volatile SingularAttribute<PortalUserOrganization, String> keywordsEn;
	public static volatile SingularAttribute<PortalUserOrganization, String> legalNameSr;
	public static volatile SingularAttribute<PortalUserOrganization, String> legalNameEn;
	public static volatile SingularAttribute<PortalUserOrganization, Long> id;
	public static volatile SingularAttribute<PortalUserOrganization, String> shortName;

	public static final String NIO_INSTITUTION = "nioInstitution";
	public static final String COUNTRY = "country";
	public static final String ADDRESS = "address";
	public static final String KEYWORDS = "keywords";
	public static final String CITY = "city";
	public static final String WEB_ADDRESS = "webAddress";
	public static final String POSTAL_CODE = "postalCode";
	public static final String PIB = "pib";
	public static final String LEGAL_STATUS = "legalStatus";
	public static final String LEGAL_NAME_SR_CYR = "legalNameSrCyr";
	public static final String REQUESTS = "requests";
	public static final String PORTAL_USERS = "portalUsers";
	public static final String RESEARCH_ORGANIZATION = "researchOrganization";
	public static final String KEYWORDS_CYR = "keywordsCyr";
	public static final String REGISTRATION_NUMBER = "registrationNumber";
	public static final String KEYWORDS_EN = "keywordsEn";
	public static final String LEGAL_NAME_SR = "legalNameSr";
	public static final String LEGAL_NAME_EN = "legalNameEn";
	public static final String ID = "id";
	public static final String SHORT_NAME = "shortName";

}

