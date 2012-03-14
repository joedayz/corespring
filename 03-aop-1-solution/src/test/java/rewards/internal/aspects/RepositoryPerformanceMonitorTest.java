package rewards.internal.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rewards.internal.monitor.jamon.JamonMonitorFactory;

/**
 * Unit test to test the behavior of the RepositoryPerformanceMonitor aspect in isolation.
 */
@RunWith(JUnit4.class)
public class RepositoryPerformanceMonitorTest {

	@Test
	public void testMonitor() throws Throwable {
		JamonMonitorFactory monitorFactory = new JamonMonitorFactory();
		RepositoryPerformanceMonitor performanceMonitor = new RepositoryPerformanceMonitor(monitorFactory);
		Signature signature = EasyMock.createMock(Signature.class);
		ProceedingJoinPoint targetMethod = EasyMock.createMock(ProceedingJoinPoint.class);

		EasyMock.expect(targetMethod.getSignature()).andReturn(signature);
		EasyMock.expect(signature.getDeclaringType()).andReturn(Object.class);
		EasyMock.expect(signature.getName()).andReturn("hashCode");
		EasyMock.expect(targetMethod.proceed()).andReturn(new Object());

		EasyMock.replay(signature, targetMethod);
		performanceMonitor.monitor(targetMethod);
		EasyMock.verify(signature, targetMethod);
	}

}
