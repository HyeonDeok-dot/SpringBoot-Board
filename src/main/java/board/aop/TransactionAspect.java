package board.aop;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class TransactionAspect {
	
	//트랙잭션 설정값
	private static final String AOP_TRANSACTION_METHOD_NAME = "*";
	private static final String AOP_TRANSACTION_EXPRESSION = "execution(* board..service.*Impl.*(..))"; 
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public TransactionInterceptor transactionAdvice(){
		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setName(AOP_TRANSACTION_METHOD_NAME); //트랜잭션의 이름
		transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class))); //트랜잭션의 롤백하는 룰을 설정, 예외가 일어나면 롤백하도록 구현
		source.setTransactionAttribute(transactionAttribute);
		
		return new TransactionInterceptor(transactionManager, source);
	}
	
	@Bean
	public Advisor transactionAdviceAdvisor(){
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_TRANSACTION_EXPRESSION); //AOP의 포인트컷 설정, ServiceImple클래스의 모든 메서드를 위의 설정에서 지정했음
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
}
