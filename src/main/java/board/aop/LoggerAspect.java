package board.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect //AOP 설정
@Slf4j
public class LoggerAspect {
	
	@Around("execution(* board..controller.*Controller.*(..)) or " //------------------
			+ "execution(* board..service.*Impl.*(..)) or "        // 기능이 실행될 시점을 지정
			+ "execution(* board..mapper.*Mapper.*(..))")          //------------------
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {  //실행되는 메서드의 이름을 이용해서 각 기능을 구분한 후 해당 메서드 출력
		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();
		if (name.indexOf("Controller") > -1) {
			type = "Controller  \t:  ";
		}
		else if (name.indexOf("Service") > -1) {
			type = "ServiceImpl  \t:  ";
		}
		else if (name.indexOf("Mapper") > -1) {
			type = "Mapper  \t\t:  ";
		}
		log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}
}
