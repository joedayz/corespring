package rewards.internal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.internal.account.AccountRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.RewardRepository;

import common.money.MonetaryAmount;

import static org.junit.Assert.*;

/**
 * Unit tests for the RewardNetworkImpl application logic. Configures the implementation with stub repositories
 * containing dummy data for fast in-memory testing without the overhead of an external data source.
 * 
 * Besides helping catch bugs early, tests are a great way for a new developer to learn an API as he or she can see the
 * API in action. Tests also help validate a design as they are a measure for how easy it is to use your code.
 */
@RunWith(JUnit4.class)
public class RewardNetworkImplTest {

	/**
	 * The object being tested.
	 */
	private RewardNetworkImpl rewardNetwork;

	@Before
	public void setUp() throws Exception {
		// create stubs to facilitate fast in-memory testing with dummy data and no external dependencies
		AccountRepository accountRepo = new StubAccountRepository();
		RestaurantRepository restaurantRepo = new StubRestaurantRepository();
		RewardRepository rewardRepo = new StubRewardRepository();

		// setup the object being tested by handing what it needs to work
		rewardNetwork = new RewardNetworkImpl(accountRepo, restaurantRepo, rewardRepo);
	}

	@Test
	public void testRewardForDining() {
		// create a new dining of 100.00 charged to credit card '1234123412341234' by merchant '123457890' as test input
		Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");

		// call the 'rewardNetwork' to test its rewardAccountFor(Dining) method
		RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);

		// assert the expected reward confirmation results
		assertNotNull(confirmation);
		assertNotNull(confirmation.getConfirmationNumber());

		// assert an account contribution was made
		AccountContribution contribution = confirmation.getAccountContribution();
		assertNotNull(contribution);

		// the account number should be '123456789'
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