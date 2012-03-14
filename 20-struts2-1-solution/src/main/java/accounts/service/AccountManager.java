package accounts.service;

import java.util.List;

import accounts.model.Account;


public interface AccountManager {

	public Account findByCreditCard(String creditCardNumber);
	
	public List<Account> getAllAccounts();
	
	public Account getAccount(Long id);
	
	public void update(Account account);
}
