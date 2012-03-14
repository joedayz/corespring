package rewards.internal.restaurant;

import rewards.Dining;
import rewards.internal.account.Account;

/**
 * A benefit availabilty policy that returns true at all times.
 */
public class AlwaysAvailable implements BenefitAvailabilityPolicy {
	static final BenefitAvailabilityPolicy INSTANCE = new AlwaysAvailable();

	public boolean isBenefitAvailableFor(Account account, Dining dining) {
		return true;
	}

	public String toString() {
		return "alwaysAvailable";
	}
}
