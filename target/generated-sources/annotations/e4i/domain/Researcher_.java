package e4i.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Researcher.class)
public abstract class Researcher_ {

	public static volatile SetAttribute<Researcher, ResearchInstitution> institutions;
	public static volatile SingularAttribute<Researcher, String> firstName;
	public static volatile SingularAttribute<Researcher, String> lastName;
	public static volatile SingularAttribute<Researcher, PortalUser> portalUser;
	public static volatile SingularAttribute<Researcher, String> middleNameLat;
	public static volatile SingularAttribute<Researcher, String> middleName;
	public static volatile SingularAttribute<Researcher, Long> id;
	public static volatile SingularAttribute<Researcher, LocalDate> birthDate;
	public static volatile SingularAttribute<Researcher, String> firstNameLat;
	public static volatile SingularAttribute<Researcher, String> lastNameLat;

	public static final String INSTITUTIONS = "institutions";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String PORTAL_USER = "portalUser";
	public static final String MIDDLE_NAME_LAT = "middleNameLat";
	public static final String MIDDLE_NAME = "middleName";
	public static final String ID = "id";
	public static final String BIRTH_DATE = "birthDate";
	public static final String FIRST_NAME_LAT = "firstNameLat";
	public static final String LAST_NAME_LAT = "lastNameLat";

}

