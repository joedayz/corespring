package rewards.internal.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import rewards.internal.account.AccountRepository;
import rewards.internal.monitor.Monitor;
import rewards.internal.monitor.MonitorFactory;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.RewardRepository;

/**
 * An aspect that monitors the performance of all three repositories used in the application.
 * @see AccountRepository
 * @see RestaurantRepository
 * @see RewardRepository
 */
@Aspect
public class RepositoryPerformanceMonitor {

	private static final Logger logger = Logger.getLogger(RepositoryPerformanceMonitor.class);

	private MonitorFactory monitorFactory;

	public RepositoryPerformanceMonitor(MonitorFactory monitorFactory) {
		this.monitorFactory = monitorFactory;
	}

	/**
	 * Times repository method invocations and outputs performance results to a Log4J logger.
	 * @param repositoryMethod The join point representing the intercepted repository method
	 * @return The object returned by the target method
	 * @throws Throwable if thrown by the target method
	 */
	@Around("anyRepositoryMethod()")
	public Object monitor(ProceedingJoinPoint repositoryMethod) throws Throwable {
		String name = createJoinPointTraceName(repositoryMethod);
		Monitor monitor = monitorFactory.start(name);
		try {
			return repositoryMethod.proceed();
		} finally {
			monitor.stop();
			logger.info(monitor);
		}
	}

	@Pointcut("anyAccountRepositoryMethod() || anyRestaurantRepositoryMethod() || anyRewardRepositoryMethod()")
	public void anyRepositoryMethod() {
	}

	@Pointcut("execution(public * rewards.internal.account.AccountRepository+.*(..))")
	public void anyAccountRepositoryMethod() {
	}

	@Pointcut("execution(public * rewards.internal.restaurant.RestaurantRepository+.*(..))")
	public void anyRestaurantRepositoryMethod() {
	}

	@Pointcut("execution(public * rewards.internal.reward.RewardRepository+.*(..))")
	public void anyRewardRepositoryMethod() {
	}

	private String createJoinPointTraceName(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		StringBuilder sb = new StringBuilder();
		sb.append(signature.getDeclaringType().getSimpleName());
		sb.append('.').append(signature.getName());
		return sb.toString();
	}
}