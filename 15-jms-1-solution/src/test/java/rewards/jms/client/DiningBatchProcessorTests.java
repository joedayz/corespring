package rewards.jms.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import rewards.Dining;

/**
 * Tests the Dining batch processor
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:rewards/system-test-config.xml",
					"classpath:rewards/jms/client/client-config.xml", 
					"classpath:rewards/jms/jms-rewards-config.xml",
					"classpath:rewards/jms/jms-infrastructure-config.xml"})
public class DiningBatchProcessorTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private DiningBatchProcessor diningBatchProcessor;

	@Autowired
	private RewardConfirmationLogger confirmationLogger;
	
	@Test
	public void testBatch() throws Exception {
		Dining dining1 = Dining.createDining("80.93", "1234123412341234", "1234567890");
		Dining dining2 = Dining.createDining("56.12", "1234123412341234", "1234567890");
		Dining dining3 = Dining.createDining("32.64", "1234123412341234", "1234567890");
		Dining dining4 = Dining.createDining("77.05", "1234123412341234", "1234567890");
		Dining dining5 = Dining.createDining("94.50", "1234123412341234", "1234567890");

		List<Dining> batch = new ArrayList<Dining>();
		batch.add(dining1);
		batch.add(dining2);
		batch.add(dining3);
		batch.add(dining4);
		batch.add(dining5);

		diningBatchProcessor.processBatch(batch);
		waitForBatch(batch.size(), 1000);

		assertEquals(batch.size(), confirmationLogger.getConfirmations().size());
	}

	private void waitForBatch(int batchSize, int timeout) throws InterruptedException {
		int sleepTime = 100;
		while (confirmationLogger.getConfirmations().size() < batchSize && timeout > 0) {
			Thread.sleep(sleepTime);
			timeout -= sleepTime;
		}
	}
}
