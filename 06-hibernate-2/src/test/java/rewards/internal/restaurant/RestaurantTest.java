package rewards.internal.restaurant;

import junit.framework.TestCase;
import rewards.Dining;
import rewards.internal.account.Account;

import common.money.MonetaryAmount;
import common.money.Percentage;

/**
 * Unit tests for exercising the behavior of the Restaurant aggregate entity. A restaurant calculates a benefit to award
 * to an account for dining based on an availability policy and benefit percentage.
 */
public class RestaurantTest extends TestCase {

	private Restaurant restaurant;

	private Account account;

	private Dining dining;

	protected void setUp() {
		// configure the restaurant, the object being tested
		restaurant = new Restaurant("1234567890", "AppleBee's");
		restaurant.setBenefitPercentage(Percentage.valueOf("8%"));
		restaurant.setBenefitAvailabilityPolicy(new StubBenefitAvailibilityPolicy(true));
		// configure supporting objects needed by the restaurant
		account = new Account("123456789", "Keith and Keri Donald");
		account.addBeneficiary("Annabelle");
		dining = Dining.createDining("100.00", "1234123412341234", "1234567890");
	}

	public void testCalcuateBenefitFor() {
		MonetaryAmount benefit = restaurant.calculateBenefitFor(account, dining);
		// assert 8.00 eligible for reward
		assertEquals(MonetaryAmount.valueOf("8.00"), benefit);
	}

	public void testNoBenefitAvailable() {
		// configure stub that always returns false
		restaurant.setBenefitAvailabilityPolicy(new StubBenefitAvailibilityPolicy(false));
		MonetaryAmount benefit = restaurant.calculateBenefitFor(account, dining);
		// assert zero eligible for reward
		assertEquals(MonetaryAmount.valueOf("0.00"), benefit);
	}

	/**
	 * A simple "dummy" benefit availability policy containing a single flag used to determine if benefit is available.
	 * Only useful for testing--a real availability policy might consider many factors such as the day of week of the
	 * dining, or the account's reward history for the current month.
	 */
	private static class StubBenefitAvailibilityPolicy implements BenefitAvailabilityPolicy {

		private boolean isBenefitAvailable;

		public StubBenefitAvailibilityPolicy(boolean isBenefitAvailable) {
			this.isBenefitAvailable = isBenefitAvailable;
		}

		public boolean isBenefitAvailableFor(Account account, Dining dining) {
			return isBenefitAvailable;
		}
	}
}