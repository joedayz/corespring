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

public class StubAccountManager implements AccountManager {

	private Map<Long, Account> accountsById = new HashMap<Long, Account>();
	private Map<String, Account> accountsByNumber = new HashMap<String, Account>();

	public StubAccountManager() {
		String number = "123456789";
		Account account = new Account(number, "Keith and Keri Donald");
		account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
		account.addBeneficiary("Corgan", Percentage.valueOf("50%"));
		account.setEntityId(0L);
		accountsById.put(Long.valueOf(0), account);
		accountsByNumber.put("123456789", account);
		
		number = "123456001";
		account = new Account(number, "Dollie R. Adams");
		account.setEntityId(1L);
		accountsById.put(Long.valueOf(1), account);
		accountsByNumber.put(number, account);
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

	public Account findAccount(String number) {
		return accountsByNumber.get(number);
	}

}
