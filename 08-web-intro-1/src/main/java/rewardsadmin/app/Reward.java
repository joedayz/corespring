package rewardsadmin.app;

import common.datetime.SimpleDate;
import common.money.MonetaryAmount;

/**
 * A record of a confirmed reward to an account from a merchant for dining.
 */
public class Reward {

	/**
	 * The unique reward confirmation number.
	 */
	private String confirmationNumber;

	/**
	 * The number of the account that accepted this reward.
	 */
	private String accountNumber;

	/**
	 * The number of the merchant who gave out this reward.
	 */
	private String merchantNumber;

	/**
	 * The date of this reward.
	 */
	private SimpleDate date;

	/**
	 * The amount of this reward.
	 */
	private MonetaryAmount amount;

	/**
	 * Creates a new reward.
	 * @param confirmationNumber the unique confirmation number
	 * @param accountNumber the number of the account that accepted the reward
	 * @param merchantNumber the nubmer of the merchant that gave out the reward
	 * @param date the reward date
	 * @param amount the reward amount
	 */
	public Reward(String confirmationNumber, String accountNumber, String merchantNumber, SimpleDate date,
			MonetaryAmount amount) {
		this.confirmationNumber = confirmationNumber;
		this.accountNumber = accountNumber;
		this.merchantNumber = merchantNumber;
		this.date = date;
		this.amount = amount;
	}

	/**
	 * Returns this reward's confirmation number, a unique record locator.
	 */
	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	/**
	 * Returns the number of the account that accepted this reward.
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Returns the number of the merchant that gave out this reward.
	 */
	public String getMerchantNumber() {
		return merchantNumber;
	}

	/**
	 * Returns the date of this reward.
	 */
	public SimpleDate getDate() {
		return date;
	}

	/**
	 * Returns the amount of this reward.
	 */
	public MonetaryAmount getAmount() {
		return amount;
	}
}