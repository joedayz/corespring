package rewards.internal.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import rewards.internal.account.AccountRepository;
import rewards.internal.monitor.Monitor;
import rewards.internal.monitor.MonitorFactory;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.RewardRepository;

/**
 * An aspect that will monitor the performance of all three repositories used in the application.
 * @see AccountRepository
 * @see RestaurantRepository
 * @see RewardRepository
 */
// TODO 1: Declare this class to be an aspect
public class RepositoryPerformanceMonitor {

	private static final Logger logger = Logger.getLogger(RepositoryPerformanceMonitor.class);

	private MonitorFactory monitorFactory;

	public RepositoryPerformanceMonitor
		(MonitorFactory monitorFactory) {
		this.monitorFactory = monitorFactory;
	}

	/**
	 * Times repository method invocations and outputs performance results to 
	 * a Log4J logger.
	 * @param repositoryMethod The join point representing the intercepted repository method
	 * @return The object returned by the target method
	 * @throws Throwable if thrown by the target method
	 */
	// TODO 2: Declare this method as an Around advice
	public Object monitor(ProceedingJoinPoint repositoryMethod)
		throws Throwable {
		String name = createJoinPointTraceName(repositoryMethod);
		Monitor monitor = monitorFactory.start(name);
		try {
			// TODO 3: Proceed with the target method invocation
			return null;
		} finally {
			monitor.stop();
			logger.info(monitor);
		}
	}

	// TODO 4: Create a pointcut that matches any method on the AccountRepository, RestaurantRepository, or
	// RewardRepository interfaces

	private String createJoinPointTraceName(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		StringBuilder sb = new StringBuilder();
		sb.append(signature.getDeclaringType().getSimpleName());
		sb.append('.').append(signature.getName());
		return sb.toString();
	}
}