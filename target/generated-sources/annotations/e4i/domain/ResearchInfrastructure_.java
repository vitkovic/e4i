package e4i.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ResearchInfrastructure.class)
public abstract class ResearchInfrastructure_ {

	public static volatile SingularAttribute<ResearchInfrastructure, Double> purchaseValueEuro;
	public static volatile SingularAttribute<ResearchInfrastructure, String> technicalSpecificationLinkSrCyr;
	public static volatile SingularAttribute<ResearchInfrastructure, String> keywords;
	public static volatile SingularAttribute<ResearchInfrastructure, String> termsOfUseEn;
	public static volatile SingularAttribute<ResearchInfrastructure, Integer> acquisitionYear;
	public static volatile SingularAttribute<ResearchInfrastructure, PortalUser> contactPerson;
	public static volatile SingularAttribute<ResearchInfrastructure, String> userManualLinkEn;
	public static volatile SingularAttribute<ResearchInfrastructure, RiAccessMode> accessMode;
	public static volatile SingularAttribute<ResearchInfrastructure, RiAccessType> accessType;
	public static volatile SingularAttribute<ResearchInfrastructure, String> descriptionSrCyr;
	public static volatile SetAttribute<ResearchInfrastructure, ScientificSubdomain> subdomains;
	public static volatile SingularAttribute<ResearchInfrastructure, String> userManualLinkSrCyr;
	public static volatile SingularAttribute<ResearchInfrastructure, String> technicalSpecificationLinkEn;
	public static volatile SingularAttribute<ResearchInfrastructure, String> userManualLinkSr;
	public static volatile SingularAttribute<ResearchInfrastructure, String> keywordsCyr;
	public static volatile SingularAttribute<ResearchInfrastructure, String> termsOfUseSrCyr;
	public static volatile SingularAttribute<ResearchInfrastructure, Long> id;
	public static volatile SingularAttribute<ResearchInfrastructure, String> technicalSpecificationSrCyr;
	public static volatile SingularAttribute<ResearchInfrastructure, Double> purchaseValueDin;
	public static volatile SingularAttribute<ResearchInfrastructure, String> termsOfUseSr;
	public static volatile SingularAttribute<ResearchInfrastructure, String> descriptionSr;
	public static volatile SingularAttribute<ResearchInfrastructure, String> departmentName;
	public static volatile SingularAttribute<ResearchInfrastructure, String> technicalSpecificationEn;
	public static volatile SingularAttribute<ResearchInfrastructure, RiResearchOrganization> owner;
	public static volatile SingularAttribute<ResearchInfrastructure, String> descriptionEn;
	public static volatile SetAttribute<ResearchInfrastructure, InfrastructureImage> images;
	public static volatile SingularAttribute<ResearchInfrastructure, String> riWebsite;
	public static volatile SingularAttribute<ResearchInfrastructure, PortalUser> manager;
	public static volatile SingularAttribute<ResearchInfrastructure, String> technicalSpecificationLinkSr;
	public static volatile SingularAttribute<ResearchInfrastructure, String> financeSource;
	public static volatile SingularAttribute<ResearchInfrastructure, String> technicalSpecificationSr;
	public static volatile SingularAttribute<ResearchInfrastructure, String> nameEn;
	public static volatile SetAttribute<ResearchInfrastructure, RiService> services;
	public static volatile SingularAttribute<ResearchInfrastructure, String> nameSrCyr;
	public static volatile SingularAttribute<ResearchInfrastructure, String> nameSr;
	public static volatile SetAttribute<ResearchInfrastructure, RiCalendar> calendars;
	public static volatile SingularAttribute<ResearchInfrastructure, String> riLogo;
	public static volatile SingularAttribute<ResearchInfrastructure, String> keywordsEn;
	public static volatile SingularAttribute<ResearchInfrastructure, String> projectName;
	public static volatile SingularAttribute<ResearchInfrastructure, RiCategory> category;
	public static volatile SetAttribute<ResearchInfrastructure, RiSubcategory> subcategories;
	public static volatile SingularAttribute<ResearchInfrastructure, RiStatus> status;

	public static final String PURCHASE_VALUE_EURO = "purchaseValueEuro";
	public static final String TECHNICAL_SPECIFICATION_LINK_SR_CYR = "technicalSpecificationLinkSrCyr";
	public static final String KEYWORDS = "keywords";
	public static final String TERMS_OF_USE_EN = "termsOfUseEn";
	public static final String ACQUISITION_YEAR = "acquisitionYear";
	public static final String CONTACT_PERSON = "contactPerson";
	public static final String USER_MANUAL_LINK_EN = "userManualLinkEn";
	public static final String ACCESS_MODE = "accessMode";
	public static final String ACCESS_TYPE = "accessType";
	public static final String DESCRIPTION_SR_CYR = "descriptionSrCyr";
	public static final String SUBDOMAINS = "subdomains";
	public static final String USER_MANUAL_LINK_SR_CYR = "userManualLinkSrCyr";
	public static final String TECHNICAL_SPECIFICATION_LINK_EN = "technicalSpecificationLinkEn";
	public static final String USER_MANUAL_LINK_SR = "userManualLinkSr";
	public static final String KEYWORDS_CYR = "keywordsCyr";
	public static final String TERMS_OF_USE_SR_CYR = "termsOfUseSrCyr";
	public static final String ID = "id";
	public static final String TECHNICAL_SPECIFICATION_SR_CYR = "technicalSpecificationSrCyr";
	public static final String PURCHASE_VALUE_DIN = "purchaseValueDin";
	public static final String TERMS_OF_USE_SR = "termsOfUseSr";
	public static final String DESCRIPTION_SR = "descriptionSr";
	public static final String DEPARTMENT_NAME = "departmentName";
	public static final String TECHNICAL_SPECIFICATION_EN = "technicalSpecificationEn";
	public static final String OWNER = "owner";
	public static final String DESCRIPTION_EN = "descriptionEn";
	public static final String IMAGES = "images";
	public static final String RI_WEBSITE = "riWebsite";
	public static final String MANAGER = "manager";
	public static final String TECHNICAL_SPECIFICATION_LINK_SR = "technicalSpecificationLinkSr";
	public static final String FINANCE_SOURCE = "financeSource";
	public static final String TECHNICAL_SPECIFICATION_SR = "technicalSpecificationSr";
	public static final String NAME_EN = "nameEn";
	public static final String SERVICES = "services";
	public static final String NAME_SR_CYR = "nameSrCyr";
	public static final String NAME_SR = "nameSr";
	public static final String CALENDARS = "calendars";
	public static final String RI_LOGO = "riLogo";
	public static final String KEYWORDS_EN = "keywordsEn";
	public static final String PROJECT_NAME = "projectName";
	public static final String CATEGORY = "category";
	public static final String SUBCATEGORIES = "subcategories";
	public static final String STATUS = "status";

}

