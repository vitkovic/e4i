package e4i.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RiStatus.class)
public abstract class RiStatus_ {

	public static volatile SingularAttribute<RiStatus, String> statusCyr;
	public static volatile SingularAttribute<RiStatus, String> meaningEn;
	public static volatile SingularAttribute<RiStatus, String> meaningCyr;
	public static volatile SingularAttribute<RiStatus, String> statusEn;
	public static volatile SingularAttribute<RiStatus, String> meaning;
	public static volatile SingularAttribute<RiStatus, Long> id;
	public static volatile SingularAttribute<RiStatus, String> status;

	public static final String STATUS_CYR = "statusCyr";
	public static final String MEANING_EN = "meaningEn";
	public static final String MEANING_CYR = "meaningCyr";
	public static final String STATUS_EN = "statusEn";
	public static final String MEANING = "meaning";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

