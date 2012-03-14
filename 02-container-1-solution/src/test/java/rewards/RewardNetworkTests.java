package rewards;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import common.money.MonetaryAmount;
//
//import junit.framework.TestCase;
//
///**
// * A system test that verifies the components of the RewardNetwork application work together to reward for dining
// * successfully. Uses Spring to bootstrap the application for use in a test environment.
// */
//
//public class RewardNetworkTests extends TestCase {
//
//	/**
//	 * The object being tested.
//	 */
//	private RewardNetwork rewardNetwork;
//
//	@Override
//	protected void setUp() {
//		// Create the test configuration for the application from two files
//		ApplicationContext context 
//		= new ClassPathXmlApplicationContext(new String[] {
//				"classpath:/rewards/internal/application-config.xml",
//				"classpath:/rewards/test-infrastructure-config.xml" });
//		// Get the bean to use to invoke the application
//		rewardNetwork = (RewardNetwork) context.getBean("rewardNetwork");
//	}
//
//	public void testRewardForDining() {
//		// create a new dining of 100.00 charged to credit card '1234123412341234' by merchant '123457890' as test input
//		Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");
//
//		// call the 'rewardNetwork' to test its rewardAccountFor(Dining) method
//		// this fails if you have selected an account without beneficiaries!
//		RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);
//
//		// assert the expected reward confirmation results
//		assertNotNull(confirmation);
//		assertNotNull(confirmation.getConfirmationNumber());
//
//		// assert an account contribution was made
//		AccountContribution contribution = confirmation.getAccountContribution();
//		assertNotNull(contribution);
//
//		// the contribution account number should be '123456789'
//		assertEquals("123456789", contribution.getAccountNumber());
//
//		// the total contribution amount should be 8.00 (8% of 100.00)
//		assertEquals(MonetaryAmount.valueOf("8.00"), contribution.getAmount());
//
//		// the total contribution amount should have been split into 2 distributions
//		assertEquals(2, contribution.getDistributions().size());
//
//		// each distribution should be 4.00 (as both have a 50% allocation)
//		assertEquals(MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Annabelle").getAmount());
//		assertEquals(MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Corgan").getAmount());
//	}
//}








//import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
//
//import common.money.MonetaryAmount;
//public class RewardNetworkTests
//	extends AbstractDependencyInjectionSpringContextTests {
//
//	/**
//	 * The object being tested.
//	 */
//	private RewardNetwork rewardNetwork;
//
//	public void setRewardNetwork(RewardNetwork rewardNetwork) {
//		this.rewardNetwork = rewardNetwork;
//	}
//
//	@Override
//	protected String[] getConfigLocations() {
//		return new String[] 
//		 { "classpath:rewards/test-infrastructure-config.xml" };
//	}
//
//	public void testRewardForDining() {
//		// create a new dining of 100.00 charged to credit card '1234123412341234' by merchant '123457890' as test input
//		Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");
//
//		// call the 'rewardNetwork' to test its rewardAccountFor(Dining) method
//		RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);
//
//		// assert the expected reward confirmation results
//		assertNotNull(confirmation);
//		assertNotNull(confirmation.getConfirmationNumber());
//
//		// assert an account contribution was made
//		AccountContribution contribution = confirmation.getAccountContribution();
//		assertNotNull(contribution);
//
//		// the contribution account number should be '123456789'
//		assertEquals("123456789", contribution.getAccountNumber());
//
//		// the total contribution amount should be 8.00 (8% of 100.00)
//		assertEquals(MonetaryAmount.valueOf("8.00"), contribution.getAmount());
//
//		// the total contribution amount should have been split into 2 distributions
//		assertEquals(2, contribution.getDistributions().size());
//
//		// each distribution should be 4.00 (as both have a 50% allocation)
//		assertEquals(MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Annabelle").getAmount());
//		assertEquals(MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Corgan").getAmount());
//	}
//}




import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.money.MonetaryAmount;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:rewards/test-infrastructure-config.xml"})
public class RewardNetworkTests {

	/**
	 * The object being tested.
	 */
	@Autowired
	private RewardNetwork rewardNetwork;

	@Test
	public void testRewardForDining() {
		// create a new dining of 100.00 charged to credit card '1234123412341234' by merchant '123457890' as test input
		Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");

		// call the 'rewardNetwork' to test its rewardAccountFor(Dining) method
		// this fails if you have selected an account without beneficiaries!
		RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);

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
