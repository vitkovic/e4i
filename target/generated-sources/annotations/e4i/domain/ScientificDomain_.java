package e4i.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScientificDomain.class)
public abstract class ScientificDomain_ {

	public static volatile SetAttribute<ScientificDomain, ScientificSubdomain> subdomains;
	public static volatile SingularAttribute<ScientificDomain, String> domain;
	public static volatile SingularAttribute<ScientificDomain, Long> id;
	public static volatile SingularAttribute<ScientificDomain, String> domainEn;
	public static volatile SingularAttribute<ScientificDomain, String> domainCyr;

	public static final String SUBDOMAINS = "subdomains";
	public static final String DOMAIN = "domain";
	public static final String ID = "id";
	public static final String DOMAIN_EN = "domainEn";
	public static final String DOMAIN_CYR = "domainCyr";

}

