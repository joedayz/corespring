package rewards.remoting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;

import common.money.MonetaryAmount;

import static org.junit.Assert.*;
/**
 * Tests for an HttpInvoker client of the reward network
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
						"TODO 7: provide the proper config file location",
						"classpath:rewards/system-test-config.xml"})
public class HttpInvokerTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private RewardNetwork rewardNetwork;
	
	@Test
	public void testHttpInvokerClient() {
		Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");

		// TODO 8: call the 'rewardAccountFor(..)' method
		RewardConfirmation confirmation = null;


		// assert the expected reward confirmation results
		assertNotNull(confirmation);
		assertNotNull(confirmation.getConfirmationNumber());

		// assert an account contribution was made
		AccountContribution contribution = confirmation.getAccountContribution();
		assertNotNull(contribution);

		// the contribution account number should be '123456789'
		assertEquals("123456789", contribution.getAccountNumber());

		// the total contribution amount should be 8.00 (8% of 100.00)
		assertEquals(MonetaryAmount.valueOf("8.00"), contribution.getAmount());

		// the total contribution amount should have been split into 2 distributions
		assertEquals(2, contribution.getDistributions().size());

		// each distribution should be 4.00 (as both have a 50% allocation)
		assertEquals(MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Annabelle").getAmount());
		assertEquals(MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Corgan").getAmount());
	}

}
