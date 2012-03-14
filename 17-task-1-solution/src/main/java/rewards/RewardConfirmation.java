package rewards;

/**
 * A summary of a confirmed reward transaction describing a contribution made to an account that was distributed among
 * the account's beneficiaries.
 */
public class RewardConfirmation {

	private String confirmationNumber;

	private AccountContribution accountContribution;

	/**
	 * Creates a new reward confirmation.
	 * @param confirmationNumber the unique confirmation number
	 * @param accountContribution a summary of the account contribution that was made
	 */
	public RewardConfirmation(String confirmationNumber, AccountContribution accountContribution) {
		this.confirmationNumber = confirmationNumber;
		this.accountContribution = accountContribution;
	}

	/**
	 * Returns the confirmation number of the reward transaction. Can be used later to lookup the transaction record.
	 */
	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	/**
	 * Returns a summary of the monetary contribution that was made to an account.
	 * @return the account contribution (the details of this reward)
	 */
	public AccountContribution getAccountContribution() {
		return accountContribution;
	}

	public String toString() {
		return confirmationNumber;
	}
}