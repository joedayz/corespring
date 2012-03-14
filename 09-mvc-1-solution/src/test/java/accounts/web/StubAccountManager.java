package accounts.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.orm.ObjectRetrievalFailureException;

import accounts.Account;
import accounts.AccountManager;

import common.money.Percentage;

/**
 * A dummy implementation of an account manager storing all account data in memory. Useful for testing only.
 */
public class StubAccountManager implements AccountManager {

	private Map<Long, Account> accountsById = new HashMap<Long, Account>();

	public StubAccountManager() {
		Account account = new Account("123456789", "Keith and Keri Donald");
		account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
		account.addBeneficiary("Corgan", Percentage.valueOf("50%"));
		account.setEntityId(0L);
		accountsById.put(Long.valueOf(0), account);
	}

	public List<Account> getAllAccounts() {
		return new ArrayList<Account>(accountsById.values());
	}

	public Account getAccount(Long id) {
		Account account = accountsById.get(id);
		if (account == null) {
			throw new ObjectRetrievalFailureException(Account.class, id);
		}
		return account;
	}

	public void update(Account account) {
		accountsById.put(account.getEntityId(), account);
	}

	public void updateBeneficiaryAllocationPercentages(Long accountId, Map<String, Percentage> allocationPercentages) {
		Account account = accountsById.get(accountId);
		for (Entry<String, Percentage> entry : allocationPercentages.entrySet()) {
			account.getBeneficiary(entry.getKey()).setAllocationPercentage(entry.getValue());
		}
	}

	public void addBeneficiary(Long accountId, String beneficiaryName) {
		accountsById.get(accountId).addBeneficiary(beneficiaryName, Percentage.zero());
	}

	public void removeBeneficiary(Long accountId, String beneficiaryName, Map<String, Percentage> allocationPercentages) {
		accountsById.get(accountId).removeBeneficiary(beneficiaryName);
		updateBeneficiaryAllocationPercentages(accountId, allocationPercentages);
	}

}
