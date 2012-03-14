package batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:batch/batch-config.xml", "classpath:rewards/system-test-config.xml" })
public class RewardBatchIntegrationTests extends AbstractTransactionalJUnit4SpringContextTests {


	@Test
	public void testConfiguration() throws Exception {
		assertNotNull(applicationContext.getBean("rewardNetwork"));
		Thread.sleep(15000);
	}
}
