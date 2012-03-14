package rewards.internal.restaurant;

import rewards.Dining;
import rewards.internal.account.Account;

/**
 * A benefit availabilty policy that returns false at all times.
 */
public class NeverAvailable implements BenefitAvailabilityPolicy {
	static final BenefitAvailabilityPolicy INSTANCE = new NeverAvailable();

	public boolean isBenefitAvailableFor(Account account, Dining dining) {
		return false;
	}

	public String toString() {
		return "neverAvailable";
	}
}
