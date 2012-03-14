package rewards.internal.account;

import common.money.MonetaryAmount;
import common.money.Percentage;
import common.repository.Entity;

/**
 * A single beneficiary allocated to an account. Each beneficiary has a name (e.g. Annabelle) and a savings balance
 * tracking how much money has been saved for he or she to date (e.g. $1000).
 */
public class Beneficiary extends Entity {

	private String name;

	private Percentage allocationPercentage;

	private MonetaryAmount savings = MonetaryAmount.valueOf("0.00");

	@SuppressWarnings("unused")
	private Beneficiary() {
	}

	/**
	 * Creates a new account beneficiary.
	 * @param name the name of the beneficiary
	 * @param allocationPercentage the beneficiary's allocation percentage within its account
	 */
	public Beneficiary(String name, Percentage allocationPercentage) {
		this.name = name;
		this.allocationPercentage = allocationPercentage;
	}

	/**
	 * Creates a new account beneficiary. This constructor should be called by privileged objects responsible for
	 * reconstituting an existing Account object from some external form such as a collection of database records.
	 * Marked package-private to indicate this constructor should never be called by general application code.
	 * @param name the name of the beneficiary
	 * @param allocationPercentage the beneficiary's allocation percentage within its account
	 * @param savings the total amount saved to-date for this beneficiary
	 */
	Beneficiary(String name, Percentage allocationPercentage, MonetaryAmount savings) {
		this.name = name;
		this.allocationPercentage = allocationPercentage;
		this.savings = savings;
	}

	/**
	 * Returns the beneficiary name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the beneficiary's allocation percentage in this account.
	 */
	public Percentage getAllocationPercentage() {
		return allocationPercentage;
	}

	/**
	 * Returns the amount of savings this beneficiary has accrued.
	 */
	public MonetaryAmount getSavings() {
		return savings;
	}

	/**
	 * Credit the amount to this beneficiary's saving balance.
	 * @param amount the amount to credit
	 */
	public void credit(MonetaryAmount amount) {
		savings = savings.add(amount);
	}

	public String toString() {
		return "name = '" + name + "', allocationPercentage = " + allocationPercentage + ", savings = " + savings + ")";
	}
}