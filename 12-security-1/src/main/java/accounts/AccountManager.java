package accounts;

import java.util.List;
import java.util.Map;

import common.money.Percentage;

/**
 * Manages access to account information.
 */
public interface AccountManager {

	/**
	 * Get all accounts in the system
	 * @return all accounts
	 */
	public List<Account> getAllAccounts();

	/**
	 * Find an account by its number.
	 * @param id the account id
	 * @return the account
	 */
	public Account getAccount(Long id);

	/**
	 * Takes a changed account and persists any changes made to it.
	 * @param account The account with changes
	 */
	public void update(Account account);

	/**
	 * Updates the allocation percentages for the entire collection of beneficiaries in an account
	 * @param accountId the account id
	 * @param allocationPercentages A map of beneficiary names and allocation percentages
	 */
	public void updateBeneficiaryAllocationPercentages(Long accountId, Map<String, Percentage> allocationPercentages);

	/**
	 * Adds a beneficiary to an account. The new beneficiary will have a 0 allocation percentage.
	 * @param accountId the account id
	 * @param beneficiaryName the name of the beneficiary to remove
	 */
	public void addBeneficiary(Long accountId, String beneficiaryName);

	/**
	 * Removes a beneficiary from an account.
	 * @param accountId the account id
	 * @param beneficiaryName the name of the beneficiary to remove
	 * @param allocationPercentages new allocation percentages
	 */
	public void removeBeneficiary(Long accountId, String beneficiaryName, Map<String, Percentage> allocationPercentages);

	/**
	 * Finds an account given an account number
	 * @param number the account number
	 * @return the account
	 */
	Account findAccount(String number);
}
