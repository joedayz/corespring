package rewards;

import java.util.Set;

import common.money.MonetaryAmount;
import common.money.Percentage;

/**
 * A summary of a monetary contribution made to an account that was distributed among the account's beneficiaries.
 * 
 * A value object. Immutable.
 */
public class AccountContribution {

	private String accountNumber;

	private MonetaryAmount amount;

	private Set<Distribution> distributions;

	/**
	 * Creates a new account contribution.
	 * @param accountNumber the number of the account the contribution was made
	 * @param amount the total contribution amount
	 * @param distributions how the contribution was distributed among the account's beneficiaries
	 */
	public AccountContribution(String accountNumber, MonetaryAmount amount, Set<Distribution> distributions) {
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.distributions = distributions;
	}

	/**
	 * Returns the number of the account this contribution was made to.
	 * @return the account number
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Returns the total amount of the contribution.
	 * @return the contribution amount
	 */
	public MonetaryAmount getAmount() {
		return amount;
	}

	/**
	 * Returns how this contribution was distributed among the account's beneficiaries.
	 * @return the contribution distributions
	 */
	public Set<Distribution> getDistributions() {
		return distributions;
	}

	/**
	 * Returns how this contribution was distributed to a single account beneficiary.
	 * @param beneficiary the name of the beneficiary e.g "Annabelle"
	 * @return a summary of how the contribution amount was distributed to the beneficiary
	 */
	public Distribution getDistribution(String beneficiary) {
		for (Distribution d : distributions) {
			if (d.beneficiary.equals(beneficiary)) {
				return d;
			}
		}
		throw new IllegalArgumentException("No such distribution for '" + beneficiary + "'");
	}

	/**
	 * A single distribution made to a beneficiary as part of an account contribution, summarizing the distribution
	 * amount and resulting total beneficiary savings.
	 * 
	 * A value object.
	 */
	public static class Distribution {

		private String beneficiary;

		private MonetaryAmount amount;

		private Percentage percentage;

		private MonetaryAmount totalSavings;

		/**
		 * Creates a new distribution.
		 * @param beneficiary the name of the account beneficiary that received a distribution
		 * @param amount the distribution amount
		 * @param percentage this distribution's percentage of the total account contribution
		 * @param totalSavings the beneficiary's total savings amount after the distribution was made
		 */
		public Distribution(String beneficiary, MonetaryAmount amount, Percentage percentage,
				MonetaryAmount totalSavings) {
			this.beneficiary = beneficiary;
			this.percentage = percentage;
			this.amount = amount;
			this.totalSavings = totalSavings;
		}

		/**
		 * Returns the name of the beneficiary.
		 */
		public String getBeneficiary() {
			return beneficiary;
		}

		/**
		 * Returns the amount of this distribution.
		 */
		public MonetaryAmount getAmount() {
			return amount;
		}

		/**
		 * Returns the percentage of this distribution relative to others in the contribution.
		 */
		public Percentage getPercentage() {
			return percentage;
		}

		/**
		 * Returns the total savings of the beneficiary after this distribution.
		 */
		public MonetaryAmount getTotalSavings() {
			return totalSavings;
		}

		public String toString() {
			return amount + " to '" + beneficiary + "' (" + percentage + ")";
		}
	}

	public String toString() {
		return "Contribution of " + amount + " to account '" + accountNumber + "' distributed " + distributions;
	}
}