package e4i.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RequestAnswer.class)
public abstract class RequestAnswer_ {

	public static volatile SingularAttribute<RequestAnswer, String> answer;
	public static volatile SingularAttribute<RequestAnswer, Long> id;
	public static volatile SingularAttribute<RequestAnswer, String> answerEn;
	public static volatile SingularAttribute<RequestAnswer, String> answerCyr;

	public static final String ANSWER = "answer";
	public static final String ID = "id";
	public static final String ANSWER_EN = "answerEn";
	public static final String ANSWER_CYR = "answerCyr";

}

